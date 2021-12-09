package me.sivieri.aoc2021.day9

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(9)
        val heightmap = Heightmap(data)
        val result = heightmap.calculateRiskLevel()
        println(result)
    }

}