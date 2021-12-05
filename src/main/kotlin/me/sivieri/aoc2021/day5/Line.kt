package me.sivieri.aoc2021.day5

import me.sivieri.aoc2021.common.Coordinate2D

data class Line(
    val start: Coordinate2D,
    val end: Coordinate2D
) {

    fun getFullLineCoordinates(diagonal: Boolean): List<Coordinate2D> =
        if (diagonal) {
            TODO()
        }
        else {
            when {
                start.x == end.x && start.y <= end.y -> (start.y..end.y).map { Coordinate2D(start.x, it) }
                start.x == end.x && start.y > end.y -> (end.y..start.y).map { Coordinate2D(start.x, it) }
                start.y == end.y && start.x <= end.x -> (start.x..end.x).map { Coordinate2D(it, start.y) }
                start.y == end.y && start.x > end.x -> (end.x..start.x).map { Coordinate2D(it, start.y) }
                else -> emptyList()
            }
        }

    companion object {

        fun parseString(line: String): Line {
            val (s, e) = line.split(" -> ", limit = 2)
            return Line(Coordinate2D.parseString(s, ","), Coordinate2D.parseString(e, ","))
        }

    }

}
