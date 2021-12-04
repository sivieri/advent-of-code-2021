package me.sivieri.aoc2021.day3

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(3)
        val processor = BinaryProcessor()
        val result = processor.calculatePowerConsumption(data)
        println(result.calculatePowerConsumption())
    }

}