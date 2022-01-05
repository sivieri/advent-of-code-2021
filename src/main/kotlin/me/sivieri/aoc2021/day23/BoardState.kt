package me.sivieri.aoc2021.day23

data class BoardState(
    val positions: Map<Int, Amphipod>
) {
    fun isSolved() = this == SOLUTION

    companion object {
        private val SOLUTION = BoardStateWithCost.fromString(
            "#############\n" +
                    "#...........#\n" +
                    "###A#B#C#D###\n" +
                    "  #A#B#C#D#  \n" +
                    "  #########  "
        ).toBoardState()
    }
}
