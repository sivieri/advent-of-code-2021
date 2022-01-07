package me.sivieri.aoc2021.day25

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(25)
        val bed = FloorBed(data)
        val steps = bed.evolve()
        println(steps)
    }

}