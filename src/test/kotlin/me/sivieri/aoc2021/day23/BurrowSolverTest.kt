package me.sivieri.aoc2021.day23

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Ignore
import org.junit.Test

class BurrowSolverTest {

    @Test
    @Ignore
    fun `part 1 example`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###B#C#B#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val expected = mapOf(
            1 to "#############\n" +
            "#...B.......#\n" +
            "###B#C#.#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  ",
            2 to "#############\n" +
            "#...B.......#\n" +
            "###B#.#C#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  ",
            3 to "#############\n" +
            "#...B.D.....#\n" +
            "###B#.#C#D###\n" +
            "  #A#.#C#A#  \n" +
            "  #########  ",
            4 to "#############\n" +
            "#.....D.....#\n" +
            "###B#.#C#D###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  ",
            5 to "#############\n" +
            "#.....D.....#\n" +
            "###.#B#C#D###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  ",
            6 to "#############\n" +
            "#.....D.D...#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  ",
            7 to "#############\n" +
            "#.....D.D.A.#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#.#  \n" +
            "  #########  ",
            8 to "#############\n" +
            "#.....D...A.#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  ",
            9 to "#############\n" +
            "#.........A.#\n" +
            "###.#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  ",
            10 to "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        )
        val result = solver.solve(2) { iteration, states ->
            val b = expected[iteration]!!
            val board = BoardState.fromString(b)
            println("Expected board $board exists: ${states.containsKey(board)}, cost: ${states.getOrDefault(board, -1)}")
        }
        assertThat(result, `is`(12521))
    }

}