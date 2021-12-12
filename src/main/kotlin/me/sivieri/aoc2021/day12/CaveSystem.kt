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
            val startVertexSize = if (startVertexName.isLowerCase()) CaveSize.SMALL else CaveSize.BIG
            val startVertex = Cave(startVertexName, startVertexSize)
            val endVertexSize = if (endVertexName.isLowerCase()) CaveSize.SMALL else CaveSize.BIG
            val endVertex = Cave(endVertexName, endVertexSize)
            g.addVertex(startVertex)
            g.addVertex(endVertex)
            g.addEdge(startVertex, endVertex)
            if (startVertex.isStart()) this.startVertex = startVertex
        }
    }

    fun findAllPaths(): List<String> {
        var paths = listOf(
            listOf(startVertex)
        )
        while (!paths.all { it.ends() }) {
            paths = paths.flatMap { path ->
                if (!path.ends()) {
                    val last = path.last()
                    g
                        .edgesOf(last)
                        .mapNotNull { edge ->
                            val otherCave = g.getOtherVertex(last, edge)
                            if (otherCave.isSmall() && path.contains(otherCave)) null
                            else path.plus(otherCave)
                        }
                }
                else listOf(path)
            }
        }
        return paths.map { path ->
            path.joinToString(",") { it.name }
        }
    }

    companion object {
        private fun List<Cave>.ends(): Boolean = this.last().isEnd()
    }

}