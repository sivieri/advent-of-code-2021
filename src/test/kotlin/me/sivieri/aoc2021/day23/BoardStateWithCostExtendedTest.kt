package me.sivieri.aoc2021.day23

import me.sivieri.aoc2021.day23.extended.AmphipodExtended
import me.sivieri.aoc2021.day23.extended.BoardStateExtended
import me.sivieri.aoc2021.day23.extended.BoardStateWithCostExtended
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class BoardStateWithCostExtendedTest {

    @Test
    fun `test string representation`() {
        val expected =
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val boardStateWithCost = BoardStateWithCostExtended(
            BoardStateExtended(mapOf(
                12 to AmphipodExtended.AMBER,
                13 to AmphipodExtended.BRONZE,
                14 to AmphipodExtended.COPPER,
                15 to AmphipodExtended.DESERT,
                16 to AmphipodExtended.AMBER,
                17 to AmphipodExtended.BRONZE,
                18 to AmphipodExtended.COPPER,
                19 to AmphipodExtended.DESERT,
                20 to AmphipodExtended.AMBER,
                21 to AmphipodExtended.BRONZE,
                22 to AmphipodExtended.COPPER,
                23 to AmphipodExtended.DESERT,
                24 to AmphipodExtended.AMBER,
                25 to AmphipodExtended.BRONZE,
                26 to AmphipodExtended.COPPER,
                27 to AmphipodExtended.DESERT
            )),
            0
        )
        assertThat(boardStateWithCost.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `test string parsing`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val expected = BoardStateWithCostExtended(
            BoardStateExtended(mapOf(
                12 to AmphipodExtended.AMBER,
                13 to AmphipodExtended.BRONZE,
                14 to AmphipodExtended.COPPER,
                15 to AmphipodExtended.DESERT,
                16 to AmphipodExtended.AMBER,
                17 to AmphipodExtended.BRONZE,
                18 to AmphipodExtended.COPPER,
                19 to AmphipodExtended.DESERT,
                20 to AmphipodExtended.AMBER,
                21 to AmphipodExtended.BRONZE,
                22 to AmphipodExtended.COPPER,
                23 to AmphipodExtended.DESERT,
                24 to AmphipodExtended.AMBER,
                25 to AmphipodExtended.BRONZE,
                26 to AmphipodExtended.COPPER,
                27 to AmphipodExtended.DESERT
            )),
            0
        )
        assertThat(BoardStateWithCostExtended.fromString(boardString), `is`(expected))
    }

    @Test
    fun `search a valid room when available returns it`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.searchValidRoomSpace(AmphipodExtended.AMBER)
        assertThat(result, containsInAnyOrder(12))
    }

    @Test
    fun `search a valid room when occupied fails`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #C#B#A#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.searchValidRoomSpace(AmphipodExtended.AMBER)
        assertThat(result, `is`(emptySet()))
    }

    @Test
    fun `search a valid hallway when available returns it`() {
        val boardString =
            "#############\n" +
            "#.......A...#\n" +
            "###.#B#A#D###\n" +
            "  #C#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.searchValidHallwaySpace(8)
        assertThat(result, containsInAnyOrder(1, 2, 4, 6, 10, 11))
    }

    @Test
    fun `search a valid hallway when occupied fails`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #C#B#A#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.searchValidHallwaySpace(8)
        assertThat(result, `is`(emptySet()))
    }

    @Test
    fun `impossible move 1`() {
        val boardString =
            "#############\n" +
            "#.........D.#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#.#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.generateValidMoves().map { it.boardState }
        assertThat(result, not(hasItem(BoardState.SOLUTION)))
    }

    @Test
    fun `do not move if not needed`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.searchValidSpace(AmphipodExtended.AMBER, 16)
        assertThat(result, `is`(emptySet()))
    }

    @Test
    fun `move if not needed but you need to make space for the other`() {
        val boardString =
            "#############\n" +
            "#.......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#C#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.searchValidSpace(AmphipodExtended.DESERT, 15)
        assertThat(result, `is`(not(emptySet())))
    }

    @Test
    fun `move if not needed but you need to make space for someone to enter`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#.#  \n" +
            "  #########  "
        val board = BoardStateWithCostExtended.fromString(boardString)
        val result = board.searchValidSpace(AmphipodExtended.DESERT, 15)
        assertThat(result, `is`(not(emptySet())))
    }

}