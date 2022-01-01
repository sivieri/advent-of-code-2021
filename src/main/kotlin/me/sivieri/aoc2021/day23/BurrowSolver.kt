package me.sivieri.aoc2021.day23

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

class BurrowSolver(input: String) {

    private val initialState = BoardState.fromString(input)
    private val states = mutableMapOf(initialState to 0)
    private var currentMin = Int.MAX_VALUE

    fun solve(limit: Int): Int {
        (1..limit).forEach { iteration ->
            println("Iteration $iteration")
            val newStates = states.flatMap { (boardState, _) ->
                val graph = GraphHelper.generateGraph(boardState)
                val (solved, notSolved) = generateMoves(graph)
                    .partition { it.isSolved() }
                solved.filter { it.cost < currentMin } +
                notSolved.filter { it.cost < states.getOrDefault(it, Int.MAX_VALUE) }
            }
            newStates.forEach { boardState -> states[boardState] = boardState.cost }
            currentMin = states
                .filter { it.key.isSolved() }
                .entries
                .minOfOrNull { it.value } ?: Int.MAX_VALUE
        }
        return currentMin
    }

    private fun generateMoves(graph: Graph<BoardCell, DefaultEdge>): List<BoardState> =
        graph
            .vertexSet()
            .filter { it.amphipod != null }
            .flatMap { cell -> generateCellMoves(graph, cell) }

    private fun generateCellMoves(
        graph: Graph<BoardCell, DefaultEdge>,
        cell: BoardCell
    ): List<BoardState> {
        TODO("Not yet implemented")
    }

}