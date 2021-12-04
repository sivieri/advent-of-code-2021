package me.sivieri.aoc2021.day1

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInputToList(1)
            .map { it.toInt() }
        val result = data
            .windowed(size = 3, step = 1)
            .map { it.sum() }
            .zipWithNext()
            .count { it.second > it.first }
        println(result)
    }

}