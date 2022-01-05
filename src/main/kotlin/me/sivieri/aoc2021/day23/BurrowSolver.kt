package me.sivieri.aoc2021.day23

class BurrowSolver(input: String) {

    private val initialState = BoardStateWithCost.fromString(input).toBoardState()
    private val states = mutableMapOf(initialState to 0)
    private var currentMin = Int.MAX_VALUE

    fun solve(limit: Int, inspection: (Int, Map<BoardState, Int>) -> Unit = { _, _ -> }): Int {
        (1..limit).forEach { iteration ->
            println("Iteration $iteration, states ${states.size}, solved ${states.filter { it.key.isSolved() }.size}")
            val newStates = calculateNewStates()
            states.putAll(newStates)
            currentMin = states
                .filter { it.key.isSolved() }
                .entries
                .minOfOrNull { it.value } ?: Int.MAX_VALUE
            inspection(iteration, states)
        }
        return currentMin
    }

    fun calculateNewStates(): Map<BoardState, Int> = states
        .map { (boardState, cost) ->
            val state = BoardStateWithCost(boardState.positions, cost)
            val (solved, notSolved) = state.generateValidMoves()
                .partition { it.isSolved() }
            (solved.filter { it.cost < currentMin } + notSolved)
                .filter { it.cost < states.getOrDefault(it.toBoardState(), Int.MAX_VALUE) }
                .map { it.toBoardState() to it.cost }
        }
        .flatten()
        .groupBy(keySelector = { it.first }, valueTransform = { it.second })
        .map { it.key to it.value.minOrNull()!! }
        .toMap()
}