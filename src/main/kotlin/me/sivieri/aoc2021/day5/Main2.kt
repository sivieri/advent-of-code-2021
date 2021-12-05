package me.sivieri.aoc2021.day5

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInputToList(5)
            .map { Line.parseString(it) }
        val maxX = data.maxOf { maxOf(it.start.x, it.end.x) }
        val maxY = data.maxOf { maxOf(it.start.y, it.end.y) }
        val oceanFloor = OceanFloor(maxX, maxY)
        oceanFloor.calculateVents(data, true)
        val result = oceanFloor.countHighConcentration()
        println(result)
    }

}