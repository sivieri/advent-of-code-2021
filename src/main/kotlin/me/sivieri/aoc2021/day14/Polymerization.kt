package me.sivieri.aoc2021.day14

class Polymerization(
    input: String
) {

    private val polymerTemplate: String
    private val rules: Map<String, String>

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
    }

    fun applyInsertions(n: Int): String = (1..n).fold(polymerTemplate) { polymer, _ ->
        polymer
            .windowed(size = 2, step = 1, partialWindows = true)
            .joinToString("") { pair ->
                if (rules[pair] != null) "${pair.first()}${rules[pair]}"
                else pair.first().toString()
            }
    }

    companion object {

        fun calculateMostMinusLeastCommon(polymer: String): Int {
            val frequencies = polymer
                .toList()
                .groupingBy { it }
                .eachCount()
            val most = frequencies.maxByOrNull { it.value }!!
            val least = frequencies.minByOrNull { it.value }!!
            return most.value - least.value
        }

    }

}