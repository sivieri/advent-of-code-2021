package me.sivieri.aoc2021.day18

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(18)
        val result = TreeNumberPairAssignment.sum(data).magnitude()
        println(result)
    }

}