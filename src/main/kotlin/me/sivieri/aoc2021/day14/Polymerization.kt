package me.sivieri.aoc2021.day14

import me.sivieri.aoc2021.combineWith
import java.lang.IllegalStateException

class Polymerization(
    input: String
) {

    private val polymerTemplate: String
    private val rules: Map<String, String>
    private val pairsFrequency: Map<String, Long>

    init {
        val (template, instructions) = input.split("\n\n", limit = 2)
        polymerTemplate = template
        rules = instructions
            .split("\n")
            .filterNot { it.isBlank() }
            .associate { line ->
                val (from, to) = line.split(" -> ", limit = 2)
                from to to
            }
        pairsFrequency = polymerTemplate
            .windowed(size = 2, step = 1, partialWindows = false)
            .groupingBy { it }
            .eachCount()
            .map { it.key to it.value.toLong() }
            .toMap()
            .plus("${polymerTemplate.last()}$END_BOOKMARK" to 1L)
    }

    fun applyInsertions(n: Int): String = (1..n).fold(polymerTemplate) { polymer, iteration ->
        println("Iteration $iteration")
        polymer
            .windowed(size = 2, step = 1, partialWindows = true)
            .joinToString("") { pair ->
                if (rules[pair] != null) "${pair.first()}${rules[pair]}"
                else pair.first().toString()
            }
    }

    fun applyInsertionsLong(n: Int): Map<String, Long> = (1..n)
        .fold(pairsFrequency) { polymerFrequency, iteration ->
            println("Iteration $iteration")
            polymerFrequency
                .map { (pair, frequency) ->
                    if (rules[pair] != null) {
                        listOf(
                            "${pair.first()}${rules[pair]}" to frequency,
                            "${rules[pair]}${pair.last()}" to frequency
                        )
                    } else {
                        listOf(pair to frequency)
                    }
                        .toMap()
                }
                .reduce { acc, element ->
                    acc.combineWith(element) { v1, v2 ->
                        when {
                            v1 == null && v2 == null -> throw IllegalStateException("Both values cannot be null")
                            v1 == null && v2 != null -> v2
                            v1 != null && v2 == null -> v1
                            else -> v1!! + v2!!
                        }
                    }
                }
        }

    companion object {
        private const val END_BOOKMARK = '#'

        fun calculateMostMinusLeastCommon(polymer: String): Int {
            val frequencies = polymer
                .toList()
                .groupingBy { it }
                .eachCount()
            val most = frequencies.maxByOrNull { it.value }!!
            val least = frequencies.minByOrNull { it.value }!!
            return most.value - least.value
        }

        fun calculateMostMinusLeastCommon(polymer: Map<String, Long>): Long {
            val frequencies = polymer
                .entries
                .map { it.key.first() to it.value }
                .groupingBy { it.first }
                .aggregate<Pair<Char, Long>, Char, Long> { _, acc, element, first ->
                    if (first) element.second.toLong()
                    else if (acc != null) acc + element.second.toLong()
                    else element.second.toLong()
                }
            val most = frequencies.maxByOrNull { it.value }!!
            val least = frequencies.minByOrNull { it.value }!!
            return most.value - least.value
        }
    }

}