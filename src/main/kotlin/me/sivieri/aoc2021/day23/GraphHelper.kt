package me.sivieri.aoc2021.day23

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

object GraphHelper {

    fun generateGraph(state: BoardState): Graph<BoardCell, DefaultEdge> {
        val g = SimpleGraph<BoardCell, DefaultEdge>(DefaultEdge::class.java)

        val v01 = BoardCell(1, checkState(state, 1))
        val v02 = BoardCell(2, checkState(state, 2))
        val v03 = BoardCell(3, checkState(state, 3))
        val v04 = BoardCell(4, checkState(state, 4))
        val v05 = BoardCell(5, checkState(state, 5))
        val v06 = BoardCell(6, checkState(state, 6))
        val v07 = BoardCell(7, checkState(state, 7))
        val v08 = BoardCell(8, checkState(state, 8))
        val v09 = BoardCell(9, checkState(state, 9))
        val v10 = BoardCell(10, checkState(state, 10))
        val v11 = BoardCell(11, checkState(state, 11))
        val v12 = BoardCell(12, checkState(state, 12))
        val v13 = BoardCell(13, checkState(state, 13))
        val v14 = BoardCell(14, checkState(state, 14))
        val v15 = BoardCell(15, checkState(state, 15))
        val v16 = BoardCell(16, checkState(state, 16))
        val v17 = BoardCell(17, checkState(state, 17))
        val v18 = BoardCell(18, checkState(state, 18))
        val v19 = BoardCell(19, checkState(state, 19))

        g.addVertex(v01)
        g.addVertex(v02)
        g.addVertex(v03)
        g.addVertex(v04)
        g.addVertex(v05)
        g.addVertex(v06)
        g.addVertex(v07)
        g.addVertex(v08)
        g.addVertex(v09)
        g.addVertex(v10)
        g.addVertex(v11)
        g.addVertex(v12)
        g.addVertex(v13)
        g.addVertex(v14)
        g.addVertex(v15)
        g.addVertex(v16)
        g.addVertex(v17)
        g.addVertex(v18)
        g.addVertex(v19)

        g.addEdge(v01, v02)
        g.addEdge(v02, v03)
        g.addEdge(v03, v04)
        g.addEdge(v04, v05)
        g.addEdge(v05, v06)
        g.addEdge(v06, v07)
        g.addEdge(v07, v08)
        g.addEdge(v08, v09)
        g.addEdge(v09, v10)
        g.addEdge(v10, v11)
        g.addEdge(v03, v12)
        g.addEdge(v12, v16)
        g.addEdge(v05, v13)
        g.addEdge(v13, v17)
        g.addEdge(v07, v14)
        g.addEdge(v14, v18)
        g.addEdge(v09, v15)
        g.addEdge(v15, v19)

        return g
    }

    private fun checkState(state: BoardState, index: Int): Amphipod? =
        when {
            state.a.first == index -> Amphipod.AMBER
            state.a.second == index -> Amphipod.AMBER
            state.b.first == index -> Amphipod.BRONZE
            state.b.second == index -> Amphipod.BRONZE
            state.c.first == index -> Amphipod.COPPER
            state.c.second == index -> Amphipod.COPPER
            state.d.first == index -> Amphipod.DESERT
            state.d.second == index -> Amphipod.DESERT
            else -> null
        }

}