package me.sivieri.aoc2021.day23

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class BurrowSolverStepTest {

    @Test
    fun `part 1 step 01`() {
        val boardString =
            "#############\n" +
            "#...........#\n" +
            "###B#C#B#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#...B.......#\n" +
            "###B#C#.#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 40)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 02`() {
        val boardString =
            "#############\n" +
            "#...B.......#\n" +
            "###B#C#.#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#...B.......#\n" +
            "###B#.#C#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 400)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 03`() {
        val boardString =
            "#############\n" +
            "#...B.......#\n" +
            "###B#.#C#D###\n" +
            "  #A#D#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#...B.D.....#\n" +
            "###B#.#C#D###\n" +
            "  #A#.#C#A#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 3000)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 04`() {
        val boardString =
            "#############\n" +
            "#...B.D.....#\n" +
            "###B#.#C#D###\n" +
            "  #A#.#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#.....D.....#\n" +
            "###B#.#C#D###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 30)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 05`() {
        val boardString =
            "#############\n" +
            "#.....D.....#\n" +
            "###B#.#C#D###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#.....D.....#\n" +
            "###.#B#C#D###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 40)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 06`() {
        val boardString =
            "#############\n" +
            "#.....D.....#\n" +
            "###.#B#C#D###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#.....D.D...#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 2000)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 07`() {
        val boardString =
            "#############\n" +
            "#.....D.D...#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#A#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#.....D.D.A.#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#.#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 3)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 08`() {
        val boardString =
            "#############\n" +
            "#.....D.D.A.#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#.#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#.....D...A.#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 3000)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 09`() {
        val boardString =
            "#############\n" +
            "#.....D...A.#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#.........A.#\n" +
            "###.#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 4000)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

    @Test
    fun `part 1 step 10`() {
        val boardString =
            "#############\n" +
            "#.........A.#\n" +
            "###.#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val solver = BurrowSolver(boardString)
        val states = solver.calculateNewStates()
        val expectedString =
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        val expected = BoardStateWithCost.fromString(expectedString, 8)
        assertThat(states, hasEntry(expected.toBoardState(), expected.cost))
    }

}