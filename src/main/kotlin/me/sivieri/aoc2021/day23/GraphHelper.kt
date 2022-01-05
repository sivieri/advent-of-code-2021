package me.sivieri.aoc2021.day23

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import org.jgrapht.graph.SimpleGraph
import java.text.FieldPosition

object GraphHelper {

    fun generateDirectedGraph(state: BoardState, rootIndex: Int): Graph<BoardCell, DefaultEdge> {
        val connections = mapOf(
            1 to listOf(2),
            2 to listOf(1, 3),
            3 to listOf(2, 4, 12),
            4 to listOf(3, 5),
            5 to listOf(4, 6, 13),
            6 to listOf(5, 7),
            7 to listOf(6, 8, 14),
            8 to listOf(7, 9),
            9 to listOf(8, 10, 15),
            10 to listOf(9, 11),
            11 to listOf(10),
            12 to listOf(3, 16),
            13 to listOf(5, 17),
            14 to listOf(7, 18),
            15 to listOf(9, 19),
            16 to listOf(12),
            17 to listOf(13),
            18 to listOf(14),
            19 to listOf(15)
        )
        val g = SimpleDirectedGraph<BoardCell, DefaultEdge>(DefaultEdge::class.java)
        val root = BoardCell(rootIndex, state.positions[rootIndex])
        g.addVertex(root)
        val processedCells = mutableSetOf(rootIndex)
        while (processedCells.size != connections.size) {
            val cur = processedCells.toList()
            cur.forEach { index ->
                val source = g.vertexSet().first { it.index == index }
                val destinations = connections[index]!!
                destinations.forEach { d ->
                    if (!processedCells.contains(d)) {
                        val c = BoardCell(d, state.positions[d])
                        g.addVertex(c)
                        g.addEdge(source, c)
                        processedCells.add(d)
                    }
                }
            }
        }
        return g
    }

}