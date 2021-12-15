package me.sivieri.aoc2021.day15

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(15)
        val riskLevelCalculator = RiskLevelCalculator(data, 5)
        val path = riskLevelCalculator.findLowestRiskPath()
        println(path.first.joinToString("\n"))
        println(path.first.size)
        println(path.second)
    }

}