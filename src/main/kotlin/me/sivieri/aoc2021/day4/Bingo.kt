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

    private fun applyNextExtraction(extraction: Int) = boards.forEach { it.mark(extraction) }

    private fun checkWinnerExistence(): Board? = boards.find { it.isWinning() }

    companion object {

        fun fromText(text: String): Bingo {
            val boards = text
                .split("\n\n")
                .map { Board.fromText(it.trim()) }
            return Bingo(boards)
        }

    }

}