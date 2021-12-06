package me.sivieri.aoc2021.day6

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(6).split(",").map { it.toInt() }
        val simulator = LanternfishModel(data)
        (1..80).forEach { _ -> simulator.simulateDay() }
        val result = simulator.fishCount()
        println(result)
    }

}