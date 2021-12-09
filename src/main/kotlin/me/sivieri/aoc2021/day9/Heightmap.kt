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
        val neighbors = extractNeighbors(x, y)
        val current = matrix[x][y]
        return if (neighbors.all { current < it.second }) Pair(Coordinate2D(x, y), current)
        else null
    }

    private fun extractNeighbors(
        x: Int,
        y: Int
    ): List<Pair<Coordinate2D, Int>> =
        listOf(
            Coordinate2D(x - 1, y),
            Coordinate2D(x + 1, y),
            Coordinate2D(x, y - 1),
            Coordinate2D(x, y + 1)
        )
            .filter { (it.x in 0 until maxX) && (it.y in 0 until maxY) }
            .map { Pair(it, matrix[it.x][it.y]) }

    fun calculateRiskLevel(): Int = findLowPoints().sumOf { it.second + 1 }

    fun findBasins(): List<List<Coordinate2D>> =
        findLowPoints()
            .map { it.first }
            .map { findBasin(it) }

    private fun findBasin(lowPoint: Coordinate2D): List<Coordinate2D> {
        val points = mutableSetOf(lowPoint)
        var latestCounter: Int
        do {
            latestCounter = points.size
            val neighbors = points.flatMap { point ->
                extractNeighbors(point.x, point.y)
                    .filter { it.second != BASIN_BORDER }
                    .map { it.first }
            }
            points.addAll(neighbors)
        } while (points.size != latestCounter)
        return points.toList()
    }

    fun findLargestBasins(): List<List<Coordinate2D>> = findBasins().sortedByDescending { it.size }.subList(0, 3)

    fun basinsToString(basins: List<List<Coordinate2D>>): String {
        val coordinates = basins.flatten()
        return matrix.indices.joinToString("\n") { x ->
            matrix[x].indices.joinToString("") { y ->
                if (Coordinate2D(x, y) in coordinates) matrix[x][y].toString()
                else "."
            }
        }
    }

    companion object {
        private const val BASIN_BORDER = 9
    }

}