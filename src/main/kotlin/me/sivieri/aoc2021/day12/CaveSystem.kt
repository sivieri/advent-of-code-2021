package me.sivieri.aoc2021.day12

import me.sivieri.aoc2021.getOtherVertex
import me.sivieri.aoc2021.isLowerCase
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

class CaveSystem(
    input: List<String>
) {

    private val g = SimpleGraph<Cave, DefaultEdge>(DefaultEdge::class.java)
    private lateinit var startVertex: Cave

    init {
        input.forEach { line ->
            val (startVertexName, endVertexName) = line.split("-", limit = 2)
            val startVertex = Cave.fromName(startVertexName)
            val endVertex = Cave.fromName(endVertexName)
            g.addVertex(startVertex)
            g.addVertex(endVertex)
            g.addEdge(startVertex, endVertex)
            if (startVertex.isStart()) this.startVertex = startVertex
        }
    }

    fun findAllPaths(doubleSmallAllowed: Boolean = false): List<String> {
        var paths = listOf(
            Path(
                listOf(startVertex),
                mapOf(startVertex to 1)
            )
        )
        while (!paths.all { it.path.ends() }) {
            paths = paths.flatMap { path ->
                if (!path.path.ends()) {
                    val last = path.path.last()
                    g
                        .edgesOf(last)
                        .mapNotNull { edge ->
                            val otherCave = g.getOtherVertex(last, edge)
                            if (
                                (otherCave.isStart() && path.path.size > 1)
                                || (!doubleSmallAllowed && otherCave.isSmall() && path.path.contains(otherCave))
                                || (doubleSmallAllowed && otherCave.isSmall() && path.smallAlreadyVisitedTwice(otherCave))
                            ) null
                            else path.addCave(otherCave)
                        }
                }
                else listOf(path)
            }
        }
        return paths.map { (path, _) ->
            path.joinToString(",") { it.name }
        }
    }

    companion object {
        private fun List<Cave>.ends(): Boolean = this.last().isEnd()
    }

}