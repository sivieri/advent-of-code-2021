package me.sivieri.aoc2021.day9

import me.sivieri.aoc2021.common.Coordinate2D

class Heightmap(
    data: List<String>
) {

    private val matrix: Array<Array<Int>> = data
        .map { line ->
            line.toList().map { it.toString().toInt() }.toTypedArray()
        }
        .toTypedArray()
    private val maxX = matrix.size
    private val maxY = matrix[0].size

    fun findLowPoints(): List<Pair<Coordinate2D, Int>> =
        (matrix.indices).flatMap { x ->
            (matrix[x].indices).mapNotNull { y ->
                checkAdjacent(x, y)
            }
        }

    private fun checkAdjacent(x: Int, y: Int): Pair<Coordinate2D, Int>? {
        val neighbors = listOf(
            Coordinate2D(x - 1, y),
            Coordinate2D(x + 1, y),
            Coordinate2D(x, y - 1),
            Coordinate2D(x, y + 1)
        )
            .filter { (it.x in 0 until maxX) && (it.y in 0 until maxY) }
            .map { Pair(it, matrix[it.x][it.y]) }
        val current = matrix[x][y]
        return if (neighbors.all { current < it.second }) Pair(Coordinate2D(x, y), current)
        else null
    }

    fun calculateRiskLevel(): Int = findLowPoints().sumOf { it.second + 1 }

}