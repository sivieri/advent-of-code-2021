package me.sivieri.aoc2021.day14

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(14)
        val polymerization = Polymerization(data)
        val polymer = polymerization.applyInsertions(10)
        val result = Polymerization.calculateMostMinusLeastCommon(polymer)
        println(result)
    }

}