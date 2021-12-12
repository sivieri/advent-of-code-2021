package me.sivieri.aoc2021.day12

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(12)
        val caveSystem = CaveSystem(data)
        val paths = caveSystem.findAllPaths()
        println(paths.size)
    }

}