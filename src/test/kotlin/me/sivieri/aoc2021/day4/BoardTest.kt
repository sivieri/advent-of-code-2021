package me.sivieri.aoc2021.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BoardTest {

    @Test
    fun `import a board as text`() {
        val text = """
            22 13 17 11  0
             8  2 23  4 24
            21  9 14 16  7
             6 10  3 18  5
             1 12 20 15 19
        """.trimIndent()
        val board = Board.fromText(text)
        val expected = Board(mutableMapOf(
            Coordinate(0, 0) to Value(22, false),
            Coordinate(1, 0) to Value(13, false),
            Coordinate(2, 0) to Value(17, false),
            Coordinate(3, 0) to Value(11, false),
            Coordinate(4, 0) to Value(0, false),
            Coordinate(0, 1) to Value(8, false),
            Coordinate(1, 1) to Value(2, false),
            Coordinate(2, 1) to Value(23, false),
            Coordinate(3, 1) to Value(4, false),
            Coordinate(4, 1) to Value(24, false),
            Coordinate(0, 2) to Value(21, false),
            Coordinate(1, 2) to Value(9, false),
            Coordinate(2, 2) to Value(14, false),
            Coordinate(3, 2) to Value(16, false),
            Coordinate(4, 2) to Value(7, false),
            Coordinate(0, 3) to Value(6, false),
            Coordinate(1, 3) to Value(10, false),
            Coordinate(2, 3) to Value(3, false),
            Coordinate(3, 3) to Value(18, false),
            Coordinate(4, 3) to Value(5, false),
            Coordinate(0, 4) to Value(1, false),
            Coordinate(1, 4) to Value(12, false),
            Coordinate(2, 4) to Value(20, false),
            Coordinate(3, 4) to Value(15, false),
            Coordinate(4, 4) to Value(19, false)
        ))
        assertThat(board, `is`(expected))
    }

    @Test
    fun `mark an existing cell`() {
        val startingBoard = Board(mutableMapOf(
            Coordinate(0, 0) to Value(14, false),
            Coordinate(1, 0) to Value(21, false),
            Coordinate(2, 0) to Value(17, false),
            Coordinate(3, 0) to Value(24, false),
            Coordinate(4, 0) to Value(4, false),
            Coordinate(0, 1) to Value(10, false),
            Coordinate(1, 1) to Value(16, false),
            Coordinate(2, 1) to Value(15, false),
            Coordinate(3, 1) to Value(9, false),
            Coordinate(4, 1) to Value(19, false),
            Coordinate(0, 2) to Value(18, false),
            Coordinate(1, 2) to Value(8, false),
            Coordinate(2, 2) to Value(23, false),
            Coordinate(3, 2) to Value(26, false),
            Coordinate(4, 2) to Value(20, false),
            Coordinate(0, 3) to Value(22, false),
            Coordinate(1, 3) to Value(11, false),
            Coordinate(2, 3) to Value(13, false),
            Coordinate(3, 3) to Value(6, false),
            Coordinate(4, 3) to Value(5, false),
            Coordinate(0, 4) to Value(2, false),
            Coordinate(1, 4) to Value(0, false),
            Coordinate(2, 4) to Value(12, false),
            Coordinate(3, 4) to Value(3, false),
            Coordinate(4, 4) to Value(7, false)
        ))
        startingBoard.mark(22)
        val resultBoard = Board(mutableMapOf(
            Coordinate(0, 0) to Value(14, false),
            Coordinate(1, 0) to Value(21, false),
            Coordinate(2, 0) to Value(17, false),
            Coordinate(3, 0) to Value(24, false),
            Coordinate(4, 0) to Value(4, false),
            Coordinate(0, 1) to Value(10, false),
            Coordinate(1, 1) to Value(16, false),
            Coordinate(2, 1) to Value(15, false),
            Coordinate(3, 1) to Value(9, false),
            Coordinate(4, 1) to Value(19, false),
            Coordinate(0, 2) to Value(18, false),
            Coordinate(1, 2) to Value(8, false),
            Coordinate(2, 2) to Value(23, false),
            Coordinate(3, 2) to Value(26, false),
            Coordinate(4, 2) to Value(20, false),
            Coordinate(0, 3) to Value(22, true),
            Coordinate(1, 3) to Value(11, false),
            Coordinate(2, 3) to Value(13, false),
            Coordinate(3, 3) to Value(6, false),
            Coordinate(4, 3) to Value(5, false),
            Coordinate(0, 4) to Value(2, false),
            Coordinate(1, 4) to Value(0, false),
            Coordinate(2, 4) to Value(12, false),
            Coordinate(3, 4) to Value(3, false),
            Coordinate(4, 4) to Value(7, false)
        ))
        assertThat(startingBoard, `is`(resultBoard))
    }

    @Test
    fun `mark a non-existing cell`() {
        val startingBoard = Board(mutableMapOf(
            Coordinate(0, 0) to Value(14, false),
            Coordinate(1, 0) to Value(21, false),
            Coordinate(2, 0) to Value(17, false),
            Coordinate(3, 0) to Value(24, false),
            Coordinate(4, 0) to Value(4, false),
            Coordinate(0, 1) to Value(10, false),
            Coordinate(1, 1) to Value(16, false),
            Coordinate(2, 1) to Value(15, false),
            Coordinate(3, 1) to Value(9, false),
            Coordinate(4, 1) to Value(19, false),
            Coordinate(0, 2) to Value(18, false),
            Coordinate(1, 2) to Value(8, false),
            Coordinate(2, 2) to Value(23, false),
            Coordinate(3, 2) to Value(26, false),
            Coordinate(4, 2) to Value(20, false),
            Coordinate(0, 3) to Value(22, false),
            Coordinate(1, 3) to Value(11, false),
            Coordinate(2, 3) to Value(13, false),
            Coordinate(3, 3) to Value(6, false),
            Coordinate(4, 3) to Value(5, false),
            Coordinate(0, 4) to Value(2, false),
            Coordinate(1, 4) to Value(0, false),
            Coordinate(2, 4) to Value(12, false),
            Coordinate(3, 4) to Value(3, false),
            Coordinate(4, 4) to Value(7, false)
        ))
        startingBoard.mark(31)
        val resultBoard = Board(mutableMapOf(
            Coordinate(0, 0) to Value(14, false),
            Coordinate(1, 0) to Value(21, false),
            Coordinate(2, 0) to Value(17, false),
            Coordinate(3, 0) to Value(24, false),
            Coordinate(4, 0) to Value(4, false),
            Coordinate(0, 1) to Value(10, false),
            Coordinate(1, 1) to Value(16, false),
            Coordinate(2, 1) to Value(15, false),
            Coordinate(3, 1) to Value(9, false),
            Coordinate(4, 1) to Value(19, false),
            Coordinate(0, 2) to Value(18, false),
            Coordinate(1, 2) to Value(8, false),
            Coordinate(2, 2) to Value(23, false),
            Coordinate(3, 2) to Value(26, false),
            Coordinate(4, 2) to Value(20, false),
            Coordinate(0, 3) to Value(22, false),
            Coordinate(1, 3) to Value(11, false),
            Coordinate(2, 3) to Value(13, false),
            Coordinate(3, 3) to Value(6, false),
            Coordinate(4, 3) to Value(5, false),
            Coordinate(0, 4) to Value(2, false),
            Coordinate(1, 4) to Value(0, false),
            Coordinate(2, 4) to Value(12, false),
            Coordinate(3, 4) to Value(3, false),
            Coordinate(4, 4) to Value(7, false)
        ))
        assertThat(startingBoard, `is`(resultBoard))
    }

    @Test
    fun `a winning board is marked as such`() {
        val board = Board(mutableMapOf(
            Coordinate(0, 0) to Value(14, true),
            Coordinate(1, 0) to Value(21, true),
            Coordinate(2, 0) to Value(17, true),
            Coordinate(3, 0) to Value(24, true),
            Coordinate(4, 0) to Value(4, true),
            Coordinate(0, 1) to Value(10, false),
            Coordinate(1, 1) to Value(16, false),
            Coordinate(2, 1) to Value(15, false),
            Coordinate(3, 1) to Value(9, true),
            Coordinate(4, 1) to Value(19, false),
            Coordinate(0, 2) to Value(18, false),
            Coordinate(1, 2) to Value(8, false),
            Coordinate(2, 2) to Value(23, true),
            Coordinate(3, 2) to Value(26, false),
            Coordinate(4, 2) to Value(20, false),
            Coordinate(0, 3) to Value(22, false),
            Coordinate(1, 3) to Value(11, true),
            Coordinate(2, 3) to Value(13, false),
            Coordinate(3, 3) to Value(6, false),
            Coordinate(4, 3) to Value(5, true),
            Coordinate(0, 4) to Value(2, true),
            Coordinate(1, 4) to Value(0, true),
            Coordinate(2, 4) to Value(12, false),
            Coordinate(3, 4) to Value(3, false),
            Coordinate(4, 4) to Value(7, true)
        ))
        assertThat(board.isWinning(), `is`(true))
    }

    @Test
    fun `a non-winning board is marked as such`() {
        val board = Board(mutableMapOf(
            Coordinate(0, 0) to Value(22, false),
            Coordinate(1, 0) to Value(13, false),
            Coordinate(2, 0) to Value(17, true),
            Coordinate(3, 0) to Value(11, true),
            Coordinate(4, 0) to Value(0, true),
            Coordinate(0, 1) to Value(8, false),
            Coordinate(1, 1) to Value(2, true),
            Coordinate(2, 1) to Value(23, true),
            Coordinate(3, 1) to Value(4, true),
            Coordinate(4, 1) to Value(24, true),
            Coordinate(0, 2) to Value(21, true),
            Coordinate(1, 2) to Value(9, true),
            Coordinate(2, 2) to Value(14, true),
            Coordinate(3, 2) to Value(16, false),
            Coordinate(4, 2) to Value(7, true),
            Coordinate(0, 3) to Value(6, false),
            Coordinate(1, 3) to Value(10, false),
            Coordinate(2, 3) to Value(3, false),
            Coordinate(3, 3) to Value(18, false),
            Coordinate(4, 3) to Value(5, true),
            Coordinate(0, 4) to Value(1, false),
            Coordinate(1, 4) to Value(12, false),
            Coordinate(2, 4) to Value(20, false),
            Coordinate(3, 4) to Value(15, false),
            Coordinate(4, 4) to Value(19, false)
        ))
        assertThat(board.isWinning(), `is`(false))
    }

    @Test
    fun `calculate the score`() {
        val board = Board(mutableMapOf(
            Coordinate(0, 0) to Value(14, true),
            Coordinate(1, 0) to Value(21, true),
            Coordinate(2, 0) to Value(17, true),
            Coordinate(3, 0) to Value(24, true),
            Coordinate(4, 0) to Value(4, true),
            Coordinate(0, 1) to Value(10, false),
            Coordinate(1, 1) to Value(16, false),
            Coordinate(2, 1) to Value(15, false),
            Coordinate(3, 1) to Value(9, true),
            Coordinate(4, 1) to Value(19, false),
            Coordinate(0, 2) to Value(18, false),
            Coordinate(1, 2) to Value(8, false),
            Coordinate(2, 2) to Value(23, true),
            Coordinate(3, 2) to Value(26, false),
            Coordinate(4, 2) to Value(20, false),
            Coordinate(0, 3) to Value(22, false),
            Coordinate(1, 3) to Value(11, true),
            Coordinate(2, 3) to Value(13, false),
            Coordinate(3, 3) to Value(6, false),
            Coordinate(4, 3) to Value(5, true),
            Coordinate(0, 4) to Value(2, true),
            Coordinate(1, 4) to Value(0, true),
            Coordinate(2, 4) to Value(12, false),
            Coordinate(3, 4) to Value(3, false),
            Coordinate(4, 4) to Value(7, true)
        ))
        val score = board.calculateScore()
        assertThat(score, `is`(188))
    }

}