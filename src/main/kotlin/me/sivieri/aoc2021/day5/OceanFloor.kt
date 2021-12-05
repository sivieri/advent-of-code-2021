package me.sivieri.aoc2021.day5

class OceanFloor(
    maxX: Int,
    maxY: Int
) {

    private val floor: Array<Array<Int>> = Array(maxX + 1) { Array(maxY + 1) { 0 } }

    fun calculateVents(lines: List<Line>) {
        lines.forEach { line ->
            line
                .getFullLineCoordinates(false)
                .forEach { coordinate ->
                    floor[coordinate.x][coordinate.y] = floor[coordinate.x][coordinate.y] + 1
                }
        }
    }

    fun countHighConcentration(): Int =
        floor.sumOf { it.count { it > 1 } }

    override fun toString(): String = floor.joinToString("\n") { line ->
        line.joinToString("") { if (it == 0) "." else it.toString() }
    }

}