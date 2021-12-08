package me.sivieri.aoc2021.day8

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(8)
        val fullDisplay = FullDisplay(data)
        val result = fullDisplay.countEasyDigitsInOutput()
        println(result)
    }

}