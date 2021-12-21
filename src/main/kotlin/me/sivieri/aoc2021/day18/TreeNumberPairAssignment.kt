package me.sivieri.aoc2021.day18

object TreeNumberPairAssignment {

    fun sum(input: List<String>): TreeNumberPair =
        input
            .filter { it.isNotBlank() }
            .map { TreeNumberPair.fromString(it) }
            .reduce { acc, treeNumberPair -> acc + treeNumberPair }

}