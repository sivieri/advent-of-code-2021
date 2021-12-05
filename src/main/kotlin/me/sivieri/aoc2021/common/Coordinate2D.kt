package me.sivieri.aoc2021.common

data class Coordinate2D(
    val x: Int,
    val y: Int
) {

    companion object {

        fun parseString(s: String, separator: String): Coordinate2D {
            val (x, y) = s.split(separator, limit = 2)
            return Coordinate2D(x.toInt(), y.toInt())
        }

    }

}
