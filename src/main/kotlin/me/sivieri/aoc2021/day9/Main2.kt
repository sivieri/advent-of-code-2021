package me.sivieri.aoc2021.day9

import me.sivieri.aoc2021.Utils
import me.sivieri.aoc2021.multiplyBy

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(9)
        val heightmap = Heightmap(data)
        val result = heightmap.findLargestBasins().multiplyBy { it.size.toLong() }
        println(result)
    }

}