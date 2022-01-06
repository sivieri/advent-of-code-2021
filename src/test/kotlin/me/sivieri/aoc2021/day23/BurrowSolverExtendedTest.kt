package me.sivieri.aoc2021.day23

import me.sivieri.aoc2021.day23.extended.BurrowSolverExtended
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Ignore
import org.junit.Test

class BurrowSolverExtendedTest {

    @Test
    @Ignore
    fun `part 1 example`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###B#C#B#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolverExtended(boardString)
        val result = solver.solve(2)
        assertThat(result, `is`(44169))
    }

}