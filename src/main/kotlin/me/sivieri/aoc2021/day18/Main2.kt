package me.sivieri.aoc2021.day18

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(18)
        val result = TreeNumberPairAssignment.maxMagnitude(data)
        println(result)
    }

}