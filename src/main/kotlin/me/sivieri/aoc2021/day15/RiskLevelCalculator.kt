package me.sivieri.aoc2021.day15

import me.sivieri.aoc2021.common.Coordinate2D
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

class RiskLevelCalculator(input: String, multipleTiles: Int = 1) {

    private val riskNumbers: Array<Array<Int>>
    private val riskVertex: Map<Coordinate2D, RiskVertex>
    private val g = SimpleDirectedWeightedGraph<RiskVertex, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
    private val maxX: Int
    private val maxY: Int
    private val actualX: Int
    private val actualY: Int

    init {
        val localNumbers = input
            .split("\n")
            .filterNot { it.isBlank() }
            .map { line -> line.toList().map { it.toString().toInt() }.toTypedArray() }
            .toTypedArray()
        actualX = localNumbers.size
        actualY = localNumbers[0].size
        maxX = actualX * multipleTiles
        maxY = actualY * multipleTiles
        riskNumbers = Array(maxX) { Array(maxY) { PLACEHOLDER } }
        (0 until multipleTiles).forEach { roundX ->
            (0 until multipleTiles).forEach { roundY ->
                localNumbers
                    .indices
                    .forEach { x ->
                        localNumbers[x]
                            .indices
                            .forEach { y ->
                                val weight = (0 .. (roundX + roundY))
                                    .fold(localNumbers[x][y]) { acc, i ->
                                        if (i == 0) acc
                                        else if (acc + 1 > 9) 1
                                        else acc + 1
                                    }
                                riskNumbers[x + actualX * roundX][y + actualY * roundY] = weight
                            }
                    }
            }
        }
        riskVertex = riskNumbers
                .indices
                .flatMap { x ->
                    riskNumbers[x]
                        .indices
                        .map { y ->
                            if (riskNumbers[x][y] == PLACEHOLDER) throw IllegalStateException("There are still some -1 around!")
                            val coordinate = Coordinate2D(x, y)
                            val vertex = RiskVertex(coordinate, riskNumbers[x][y])
                            g.addVertex(vertex)
                            coordinate to vertex
                        }
                }
                .toMap()
        riskVertex
            .forEach { (coordinate, vertex) ->
                listOf(
                    Coordinate2D(coordinate.x + 1, coordinate.y),
                    Coordinate2D(coordinate.x, coordinate.y + 1),
                    Coordinate2D(coordinate.x - 1, coordinate.y),
                    Coordinate2D(coordinate.x, coordinate.y - 1)
                )
                    .filter { it.x < maxX && it.y < maxY && it.x >= 0 && it.y >= 0 }
                    .map { neighbor ->
                        val otherVertex = riskVertex[neighbor]!!
                        g.addEdge(vertex, otherVertex)
                        g.setEdgeWeight(vertex, otherVertex, otherVertex.weight.toDouble())
                    }
            }
    }

    fun findLowestRiskPath(): Pair<MutableList<RiskVertex>, Int> {
        val start = riskVertex[Coordinate2D(0, 0)]!!
        val end = riskVertex[Coordinate2D(maxX - 1, maxY - 1)]!!
        return DijkstraShortestPath
            .findPathBetween(g, start, end)
            .let { Pair(it.vertexList, it.vertexList.sumOf { it.weight } - start.weight) }
    }

    fun matrixToString(): String = riskNumbers
        .joinToString("\n") { row ->
            row.joinToString("")
        }

    override fun toString(): String = g.toString()

    companion object {
        private const val PLACEHOLDER = -1
    }
}