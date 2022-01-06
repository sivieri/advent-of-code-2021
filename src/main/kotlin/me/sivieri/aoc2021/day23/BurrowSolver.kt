package me.sivieri.aoc2021.day23

class BurrowSolver(input: String) {

    private val initialState = BoardState.fromString(input)
    private val states = mutableMapOf(initialState to 0)
    private var currentMin = Int.MAX_VALUE

    fun solve(historySize: Int, inspection: (Int, Map<BoardState, Int>) -> Unit = { _, _ -> }): Int {
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

    fun calculateNewStates(): Map<BoardState, Int> = states
        .map { (boardState, cost) ->
            val state = BoardStateWithCost(boardState, cost)
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