package me.sivieri.aoc2021.day14

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(14)
        val polymerization = Polymerization(data)
        val polymer = polymerization.applyInsertionsLong(40)
        val result = Polymerization.calculateMostMinusLeastCommon(polymer)
        println(result)
    }

}