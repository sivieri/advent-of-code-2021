package me.sivieri.aoc2021.day23

import me.sivieri.aoc2021.getOtherVertex
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
        sourceCell: BoardCell
    ): List<BoardState> {
        val sourceAmphipod = sourceCell.amphipod!!
        return graph
            .edgesOf(sourceCell)
            .map { edge ->
                graph.getOtherVertex(sourceCell, edge) to sourceAmphipod.cost
            }
            .filter { it.first.amphipod == null } // RULE: unoccupied space
            .flatMap { (cell, cost) -> // RULE: do not stop outside a room
                if (OUTSIDE_CELLS.contains(cell.index)) {
                    graph.edgesOf(cell)
                        .map { edge ->
                            graph.getOtherVertex(cell, edge) to cost * 2
                        }
                        .filter { it.first != sourceCell }
                }
                else listOf(cell to cost)
            }
            .filter { (cell, _) -> // RULE: enter only the final room
                (
                    BoardState.HALLWAY.contains(sourceCell.index) &&
                    sourceAmphipod.roomIndexes.contains(cell.index)
                ) || BoardState.HALLWAY.contains(cell.index)
            }
            .filter { (cell, _) -> // RULE: enter the room only if other final pods are in
                val rooms = sourceAmphipod.roomIndexes
                val vertices = graph
                    .vertexSet()
                    .filter { rooms.contains(it.index) }
                vertices.all { it.amphipod == null || it.amphipod == sourceAmphipod } ||
                BoardState.HALLWAY.contains(cell.index)
            }
            .mapNotNull { (destinationCell, cost) ->
                val amphipod = sourceCell.amphipod
                destinationCell.amphipod = amphipod
                sourceCell.amphipod = null
                val state = BoardState.fromGraph(graph, cost)
                sourceCell.amphipod = amphipod
                destinationCell.amphipod = null
                state
            }
    }

    companion object {
        private val OUTSIDE_CELLS = listOf(3, 5, 7, 9)
    }

}