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
            mapOf(
                12 to Amphipod.AMBER,
                13 to Amphipod.BRONZE,
                14 to Amphipod.COPPER,
                15 to Amphipod.DESERT,
                16 to Amphipod.AMBER,
                17 to Amphipod.BRONZE,
                18 to Amphipod.COPPER,
                19 to Amphipod.DESERT,
            ),
            0
        )
        assertThat(boardState.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `test string parsing`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val expected = BoardState(
            mapOf(
                12 to Amphipod.AMBER,
                13 to Amphipod.BRONZE,
                14 to Amphipod.COPPER,
                15 to Amphipod.DESERT,
                16 to Amphipod.AMBER,
                17 to Amphipod.BRONZE,
                18 to Amphipod.COPPER,
                19 to Amphipod.DESERT,
            ),
            0
        )
        assertThat(BoardState.fromString(boardString), `is`(expected))
    }

}