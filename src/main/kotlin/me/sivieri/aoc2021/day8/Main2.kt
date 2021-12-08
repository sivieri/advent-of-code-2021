package me.sivieri.aoc2021.day8

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(8)
        val fullDisplay = FullDisplay(data)
        val result = fullDisplay.sumTotal()
        println(result)
    }

}