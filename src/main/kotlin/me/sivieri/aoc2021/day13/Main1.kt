package me.sivieri.aoc2021.day13

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(13)
        val origami = Origami(data)
        origami.applyFold(origami.instructions[0])
        val result = origami.countDots()
        println(result)
    }

}