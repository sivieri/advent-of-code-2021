package me.sivieri.aoc2021.day4

data class Value(
    val number: Int,
    val marked: Boolean
)

data class Coordinate(
    val x: Int,
    val y: Int
)

data class Board(
    private val squares: MutableMap<Coordinate, Value>
) {

    fun mark(number: Int) {
        val current = squares.filter { it.value.number == number }.entries.firstOrNull()
        current?.let { squares[current.key] = Value(number, true) }
    }

    fun isWinning(): Boolean {
        (0 until size).forEach { x ->
            if (squares.filter { it.key.x == x }.all { it.value.marked }) return@isWinning true
        }
        (0 until size).forEach { y ->
            if (squares.filter { it.key.y == y }.all { it.value.marked }) return@isWinning true
        }

        return false
    }

    fun calculateScore(): Int = squares.filter { !it.value.marked }.entries.sumOf { it.value.number }

    companion object {

        private const val size = 5

        fun fromText(text: String): Board {
            val squares = text
                .split("\n")
                .flatMapIndexed { y, s ->
                    s.trim().split("\\s+".toRegex()).mapIndexed { x, s ->
                        Coordinate(x, y) to Value(s.toInt(), false)
                    }
                }
                .toMap()
                .toMutableMap()
            return Board(squares)
        }

    }
}
