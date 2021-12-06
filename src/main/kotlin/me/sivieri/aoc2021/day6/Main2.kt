package me.sivieri.aoc2021.day6

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(6).split(",").map { it.toInt() }
        val simulator = LanternfishOptimizedModel(data)
        val result = simulator.simulate(256)
        println(result)
    }

}