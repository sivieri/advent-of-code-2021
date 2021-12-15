package me.sivieri.aoc2021.day15

import me.sivieri.aoc2021.common.Coordinate2D
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

class RiskLevelCalculator(input: String) {

    private val riskNumbers: Array<Array<Int>>
    private val riskVertex: Map<Coordinate2D, RiskVertex>
    private val g = SimpleDirectedWeightedGraph<RiskVertex, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
    private val maxX: Int
    private val maxY: Int

    init {
        riskNumbers = input
            .split("\n")
            .filterNot { it.isBlank() }
            .map { line -> line.toList().map { it.toString().toInt() }.toTypedArray() }
            .toTypedArray()
        maxX = riskNumbers.size
        maxY = riskNumbers[0].size
        riskVertex = riskNumbers
            .indices
            .flatMap { x ->
                riskNumbers[x]
                    .indices
                    .map { y ->
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
                    Coordinate2D(coordinate.x, coordinate.y + 1)
                )
                    .filter { it.x < maxX && it.y < maxY }
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

    override fun toString(): String = g.toString()
}