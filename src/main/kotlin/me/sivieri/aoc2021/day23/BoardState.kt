package me.sivieri.aoc2021.day23

data class BoardState(
    val positions: Map<Int, Amphipod>
) {
    fun isSolved() = this == SOLUTION

    fun stringRepresentation(): String {
        val firstLine = List(13) { "#" }
            .joinToString("")
        val secondLine = listOf(
            listOf("#"),
            (1..11).map { index -> getCell(index) },
            listOf("#")
        ).flatten().joinToString("")
        val thirdLine = listOf(
            listOf("###"),
            listOf(getCell(12)),
            listOf("#"),
            listOf(getCell(13)),
            listOf("#"),
            listOf(getCell(14)),
            listOf("#"),
            listOf(getCell(15)),
            listOf("###")
        ).flatten().joinToString("")
        val fourthLine = listOf(
            listOf("  #"),
            listOf(getCell(16)),
            listOf("#"),
            listOf(getCell(17)),
            listOf("#"),
            listOf(getCell(18)),
            listOf("#"),
            listOf(getCell(19)),
            listOf("#  ")
        ).flatten().joinToString("")
        val fifthLine = listOf(
            List(2) { " " },
            List(9) { "#" },
            List(2) { " " }
        ).flatten().joinToString("")
        return listOf(
            firstLine,
            secondLine,
            thirdLine,
            fourthLine,
            fifthLine
        ).joinToString("\n")
    }

    private fun getCell(index: Int): String =
        positions[index]?.symbol?.toString() ?: "."

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
