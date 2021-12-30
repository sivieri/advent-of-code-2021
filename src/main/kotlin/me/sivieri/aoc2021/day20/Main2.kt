package me.sivieri.aoc2021.day20

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(20)
        val enhancer = ImageEnhancer(data)
        val result = enhancer.enhance(50)
        println(result.stringRepresentation())
        println(result.countLight())
    }

}