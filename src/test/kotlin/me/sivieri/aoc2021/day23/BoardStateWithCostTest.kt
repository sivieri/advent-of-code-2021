package me.sivieri.aoc2021.day23

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class BoardStateWithCostTest {

    @Test
    fun `test string representation`() {
        val expected =
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val boardStateWithCost = BoardStateWithCost(
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
        assertThat(boardStateWithCost.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `test string parsing`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val expected = BoardStateWithCost(
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
        assertThat(BoardStateWithCost.fromString(boardString), `is`(expected))
    }

    @Test
    fun `search a valid room when available returns it`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val result = board.searchValidRoomSpace(Amphipod.AMBER)
        assertThat(result, containsInAnyOrder(12))
    }

    @Test
    fun `search a valid room when occupied fails`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #C#B#A#D#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val result = board.searchValidRoomSpace(Amphipod.AMBER)
        assertThat(result, `is`(emptySet()))
    }

    @Test
    fun `search a valid hallway when available returns it`() {
        val boardString =
            "#############\n" +
            "#.......A...#\n" +
            "###.#B#A#D###\n" +
            "  #C#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
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
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val result = board.searchValidHallwaySpace(8)
        assertThat(result, `is`(emptySet()))
    }

    @Test
    fun `do not move if not needed`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val result = board.searchValidSpace(Amphipod.AMBER, 16)
        assertThat(result, `is`(emptySet()))
    }

    @Test
    fun `move if not needed but you need to make space for the other`() {
        val boardString =
            "#############\n" +
            "#.......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#C#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val result = board.searchValidSpace(Amphipod.DESERT, 15)
        assertThat(result, `is`(not(emptySet())))
    }

    @Test
    fun `move if not needed but you need to make space for someone to enter`() {
        val boardString =
            "#############\n" +
            "#C......A...#\n" +
            "###.#B#.#D###\n" +
            "  #A#B#C#.#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val result = board.searchValidSpace(Amphipod.DESERT, 15)
        assertThat(result, `is`(not(emptySet())))
    }

    @Test
    fun `search all valid moves`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###B#C#B#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val moves = board.generateValidMoves()
        val expected = listOf(
            BoardStateWithCost.fromString("#############\n" +
                    "#B..........#\n" +
                    "###.#C#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 30),
            BoardStateWithCost.fromString("#############\n" +
                    "#.B.........#\n" +
                    "###.#C#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 20),
            BoardStateWithCost.fromString("#############\n" +
                    "#...B.......#\n" +
                    "###.#C#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 20),
            BoardStateWithCost.fromString("#############\n" +
                    "#.....B.....#\n" +
                    "###.#C#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 40),
            BoardStateWithCost.fromString("#############\n" +
                    "#.......B...#\n" +
                    "###.#C#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 60),
            BoardStateWithCost.fromString("#############\n" +
                    "#.........B.#\n" +
                    "###.#C#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 80),
            BoardStateWithCost.fromString("#############\n" +
                    "#..........B#\n" +
                    "###.#C#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 90),

            BoardStateWithCost.fromString("#############\n" +
                    "#B..........#\n" +
                    "###B#C#.#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 70),
            BoardStateWithCost.fromString("#############\n" +
                    "#.B.........#\n" +
                    "###B#C#.#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 60),
            BoardStateWithCost.fromString("#############\n" +
                    "#...B.......#\n" +
                    "###B#C#.#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 40),
            BoardStateWithCost.fromString("#############\n" +
                    "#.....B.....#\n" +
                    "###B#C#.#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 20),
            BoardStateWithCost.fromString("#############\n" +
                    "#.......B...#\n" +
                    "###B#C#.#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 20),
            BoardStateWithCost.fromString("#############\n" +
                    "#.........B.#\n" +
                    "###B#C#.#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 40),
            BoardStateWithCost.fromString("#############\n" +
                    "#..........B#\n" +
                    "###B#C#.#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 50),

            BoardStateWithCost.fromString("#############\n" +
                    "#C..........#\n" +
                    "###B#.#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 500),
            BoardStateWithCost.fromString("#############\n" +
                    "#.C.........#\n" +
                    "###B#.#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 400),
            BoardStateWithCost.fromString("#############\n" +
                    "#...C.......#\n" +
                    "###B#.#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 200),
            BoardStateWithCost.fromString("#############\n" +
                    "#.....C.....#\n" +
                    "###B#.#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 200),
            BoardStateWithCost.fromString("#############\n" +
                    "#.......C...#\n" +
                    "###B#.#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 400),
            BoardStateWithCost.fromString("#############\n" +
                    "#.........C.#\n" +
                    "###B#.#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 600),
            BoardStateWithCost.fromString("#############\n" +
                    "#..........C#\n" +
                    "###B#.#B#D###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 700),

            BoardStateWithCost.fromString("#############\n" +
                    "#D..........#\n" +
                    "###B#C#B#.###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 9000),
            BoardStateWithCost.fromString("#############\n" +
                    "#.D.........#\n" +
                    "###B#C#B#.###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 8000),
            BoardStateWithCost.fromString("#############\n" +
                    "#...D.......#\n" +
                    "###B#C#B#.###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 6000),
            BoardStateWithCost.fromString("#############\n" +
                    "#.....D.....#\n" +
                    "###B#C#B#.###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 4000),
            BoardStateWithCost.fromString("#############\n" +
                    "#.......D...#\n" +
                    "###B#C#B#.###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 2000),
            BoardStateWithCost.fromString("#############\n" +
                    "#.........D.#\n" +
                    "###B#C#B#.###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 2000),
            BoardStateWithCost.fromString("#############\n" +
                    "#..........D#\n" +
                    "###B#C#B#.###\n" +
                    "  #A#D#C#A#  \n" +
                    "  #########  ", 3000),
        )
        assertThat(moves, containsInAnyOrder(*expected.toTypedArray()))
    }

}