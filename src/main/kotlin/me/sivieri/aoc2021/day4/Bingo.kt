package me.sivieri.aoc2021.day4

class Bingo(
    private val boards: List<Board>
) {

    fun play(numbers: List<Int>): Pair<Int, Board?> = numbers
        .fold(Pair<Int, Board?>(-1, null)) { acc, value ->
            if (acc.second != null) acc
            else {
                applyNextExtraction(value)
                Pair(value, checkWinnerExistence())
            }
        }

    fun findLastWinner(numbers: List<Int>): Pair<Int, Board?> = numbers
        .fold(Triple<Int, Board?, Boolean>(-1, null, false)) { acc, value ->
            if (acc.third) acc
            else {
                applyNextExtraction(value)
                val nonWinningBoards = getNonWinningBoards()
                when (nonWinningBoards.size) {
                    1 -> Triple(value, nonWinningBoards.first(), false)
                    0 -> Triple(value, acc.second, true)
                    else -> Triple(value, null, false)
                }
            }
        }
        .let { Pair(it.first, it.second) }

    private fun applyNextExtraction(extraction: Int) = boards.forEach { it.mark(extraction) }

    private fun checkWinnerExistence(): Board? = boards.find { it.isWinning() }

    private fun getNonWinningBoards(): List<Board> = boards.filter { !it.isWinning() }

    companion object {

        fun fromText(text: String): Bingo {
            val boards = text
                .split("\n\n")
                .map { Board.fromText(it.trim()) }
            return Bingo(boards)
        }

    }

}