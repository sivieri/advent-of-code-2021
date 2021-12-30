package me.sivieri.aoc2021.day20

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(20)
        val enhancer = ImageEnhancer(data)
        val result = enhancer.enhance(2)
        println(result.stringRepresentation())
        println(result.countLight())
    }

}