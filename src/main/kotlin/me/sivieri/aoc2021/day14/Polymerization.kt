package me.sivieri.aoc2021.day14

class Polymerization(
    input: String
) {

    private val polymerTemplate: String
    private val rules: Map<String, String>
    private var polymer: String

    init {
        val (template, instructions) = input.split("\n\n", limit = 2)
        polymerTemplate = template
        polymer = template
        rules = instructions
            .split("\n")
            .associate { line ->
                val (from, to) = line.split(" -> ", limit = 2)
                from to to
            }
    }

    fun applyInsertions(n: Int): String {
        TODO()
    }

    companion object {

        fun calculateMostMinusLeastCommon(polymer: String): Int {
            TODO()
        }

    }

}