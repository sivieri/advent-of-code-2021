package me.sivieri.aoc2021.day23

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(23)
        val solver = BurrowSolver(data)
        val result = solver.solve(1000)
        println(result)
    }

}