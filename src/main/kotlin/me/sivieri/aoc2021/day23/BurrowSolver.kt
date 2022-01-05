package me.sivieri.aoc2021.day23

class BurrowSolver(input: String) {

    private val initialState = BoardState.fromString(input)
    private val states = mutableMapOf(initialState to 0)
    private var currentMin = Int.MAX_VALUE

    fun solve(limit: Int): Int {
        (1..limit).forEach { iteration ->
            println("Iteration $iteration")
            val newStates = states.flatMap { (boardState, _) ->
                val (solved, notSolved) = boardState.generateValidMoves()
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

}