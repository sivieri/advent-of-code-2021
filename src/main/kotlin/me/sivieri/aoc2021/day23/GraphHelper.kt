package me.sivieri.aoc2021.day23

import me.sivieri.aoc2021.day23.extended.BoardCellExtended
import me.sivieri.aoc2021.day23.extended.BoardStateExtended
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph

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

    fun generateExtendedDirectedGraph(state: BoardStateExtended, rootIndex: Int): Graph<BoardCellExtended, DefaultEdge> {
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
            16 to listOf(12, 20),
            17 to listOf(13, 21),
            18 to listOf(14, 22),
            19 to listOf(15, 23),
            20 to listOf(16, 24),
            21 to listOf(17, 25),
            22 to listOf(18, 26),
            23 to listOf(19, 27),
            24 to listOf(20),
            25 to listOf(21),
            26 to listOf(22),
            27 to listOf(23)
        )
        val g = SimpleDirectedGraph<BoardCellExtended, DefaultEdge>(DefaultEdge::class.java)
        val root = BoardCellExtended(rootIndex, state.positions[rootIndex])
        g.addVertex(root)
        val processedCells = mutableSetOf(rootIndex)
        while (processedCells.size != connections.size) {
            val cur = processedCells.toList()
            cur.forEach { index ->
                val source = g.vertexSet().first { it.index == index }
                val destinations = connections[index]!!
                destinations.forEach { d ->
                    if (!processedCells.contains(d)) {
                        val c = BoardCellExtended(d, state.positions[d])
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