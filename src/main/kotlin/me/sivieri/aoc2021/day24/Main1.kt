package me.sivieri.aoc2021.day24

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(24)
        val tester = MonadTester(data)
        val result = tester.searchNumber()
        println(result)
    }

}