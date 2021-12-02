package me.sivieri.aoc2021.day2

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(2)
        val parser = InstructionParser()
        val finalPosition = parser.parseWithAim(data)
        val result = finalPosition.horizontalPosition * finalPosition.depth
        println(result)
    }

}