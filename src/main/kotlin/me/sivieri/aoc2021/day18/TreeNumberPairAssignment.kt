package me.sivieri.aoc2021.day18

import me.sivieri.aoc2021.cartesianProduct

object TreeNumberPairAssignment {

    fun sum(input: List<String>): TreeNumberPair =
        input
            .filter { it.isNotBlank() }
            .map { TreeNumberPair.fromString(it) }
            .reduce { acc, treeNumberPair -> acc + treeNumberPair }

    fun maxMagnitude(input: List<String>): Long {
        val numbers = input
            .filter { it.isNotBlank() }
        return cartesianProduct(numbers, numbers)
            .filterNot { it.first == it.second }
            .maxOf { (TreeNumberPair.fromString(it.first) + TreeNumberPair.fromString(it.second)).magnitude() }
    }

}