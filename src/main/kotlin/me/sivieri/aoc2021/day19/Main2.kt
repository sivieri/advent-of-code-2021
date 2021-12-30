package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(19)
        val detector = BeaconDetector(data)
        val distance = detector.findLargestManhattanDistance()
        println(distance)
    }

}