package me.sivieri.aoc2021.day23

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class BoardStateTest {

    @Test
    fun `test string representation`() {
        val expected =
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val boardState = BoardState(
            Pair(12, 16),
            Pair(13, 17),
            Pair(14, 18),
            Pair(15, 19)
        )
        assertThat(boardState.stringRepresentation(), `is`(expected))
    }

}