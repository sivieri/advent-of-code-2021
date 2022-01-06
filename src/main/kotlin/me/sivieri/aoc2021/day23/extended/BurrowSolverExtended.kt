package me.sivieri.aoc2021.day23.extended

import kotlinx.coroutines.runBlocking
import me.sivieri.aoc2021.pmap

class BurrowSolverExtended(input: String) {

    private val initialState: BoardStateExtended
    private val states: MutableMap<BoardStateExtended, Int>
    private var currentMin = Int.MAX_VALUE

    init {
        val splitted = input.split("\n")
        initialState = BoardStateExtended.fromString(
            (splitted.subList(0, 3) + MISSING_LINES + splitted.subList(3, 5)).joinToString("\n")
        )
        states = mutableMapOf(initialState to 0)
    }

    fun solve(historySize: Int, inspection: (Int, Map<BoardStateExtended, Int>) -> Unit = { _, _ -> }): Int {
        println("Initial state:\n${initialState.stringRepresentation()}")
        var history = List(historySize) { Int.MAX_VALUE }
        var iteration = 1
        while (!isStable(history)) {
            println("Iteration $iteration, states ${states.size}, solved ${states.filter { it.key.isSolved() }.size}, min: $currentMin")
            val newStates = calculateNewStates()
            states.putAll(newStates)
            currentMin = states
                .filter { it.key.isSolved() }
                .entries
                .minOfOrNull { it.value } ?: Int.MAX_VALUE
            inspection(iteration, states)
            history = updateHistory(history)
            iteration++
        }
        return currentMin
    }

    private fun updateHistory(history: List<Int>): List<Int> = listOf(currentMin) + history.subList(0, history.size - 1)

    private fun isStable(history: List<Int>): Boolean = history.all { it != Int.MAX_VALUE } && history.distinct().size == 1

    fun calculateNewStates(): Map<BoardStateExtended, Int> = runBlocking {
        states
            .pmap { (boardState, cost) ->
                val state = BoardStateWithCostExtended(boardState, cost)
                val (solved, notSolved) = state.generateValidMoves()
                    .partition { it.isSolved() }
                (solved.filter { it.cost < currentMin } + notSolved)
                    .filter { it.cost < states.getOrDefault(it.boardState, Int.MAX_VALUE) }
                    .map { it.boardState to it.cost }
            }
            .flatten()
            .groupBy(keySelector = { it.first }, valueTransform = { it.second })
            .map { it.key to it.value.minOrNull()!! }
            .toMap()
    }

    companion object {
        private val MISSING_LINES = listOf(
            "  #D#C#B#A#",
            "  #D#B#A#C#"
        )
    }
}