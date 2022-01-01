package me.sivieri.aoc2021.day23

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class BurrowSolverTest {

    @Test
    fun `part 1 example`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###B#C#B#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val result = solver.solve(1000)
        assertThat(result, `is`(12521))
    }

}