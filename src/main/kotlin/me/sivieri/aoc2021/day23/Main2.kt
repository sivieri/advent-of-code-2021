package me.sivieri.aoc2021.day23

import me.sivieri.aoc2021.Utils
import me.sivieri.aoc2021.day23.extended.BurrowSolverExtended

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(23)
        val solver = BurrowSolverExtended(data)
        val result = solver.solve(5)
        println(result)
    }

}