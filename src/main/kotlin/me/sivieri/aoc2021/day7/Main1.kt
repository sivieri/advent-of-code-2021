package me.sivieri.aoc2021.day7

import me.sivieri.aoc2021.Utils
import me.sivieri.aoc2021.toIntList

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(7).toIntList()
        val alignmentSystem = AlignmentSystem(data)
        val result = alignmentSystem.findBestAlignmentAtConstantConsumption()
        println(result)
    }

}