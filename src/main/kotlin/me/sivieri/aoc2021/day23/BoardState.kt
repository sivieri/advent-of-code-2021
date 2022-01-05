package me.sivieri.aoc2021.day23

/**
 * #############
 * #...........#
 * ###A#B#C#D###
 *   #A#B#C#D#
 *   #########
 *
 * Position indexes:
 *
 * 01 02 03 04 05 06 07 08 09 10 11
 *       12    13    14    15
 *       16    17    18    19
 */
data class BoardState(
    val positions: Map<Int, Amphipod>,
    val cost: Int
) {

    fun generateValidMoves(): List<BoardState> {
        TODO()
    }

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

    fun isSolved() = this == SOLUTION

    companion object {
        private val HALLWAY = (1..11).toList()
        private val OUTSIDE_VALUES = listOf(3, 5, 7, 9)
        private val SOLUTION = fromString(
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        )

        fun fromString(s: String): BoardState {
            val (_, hallway, side1, side2, _) = s.split("\n", limit = 5)
            val positions = mutableMapOf<Int, Amphipod>()
            hallway
                .toList()
                .forEachIndexed { index, c ->
                    val amphipod = Amphipod.fromSymbolOrNull(c)
                    if (amphipod != null) positions[index] = amphipod
                }
            if (Amphipod.fromSymbolOrNull(side1[3]) != null) positions[12] = Amphipod.fromSymbolOrNull(side1[3])!!
            if (Amphipod.fromSymbolOrNull(side1[5]) != null) positions[13] = Amphipod.fromSymbolOrNull(side1[5])!!
            if (Amphipod.fromSymbolOrNull(side1[7]) != null) positions[14] = Amphipod.fromSymbolOrNull(side1[7])!!
            if (Amphipod.fromSymbolOrNull(side1[9]) != null) positions[15] = Amphipod.fromSymbolOrNull(side1[9])!!
            if (Amphipod.fromSymbolOrNull(side2[3]) != null) positions[16] = Amphipod.fromSymbolOrNull(side2[3])!!
            if (Amphipod.fromSymbolOrNull(side2[5]) != null) positions[17] = Amphipod.fromSymbolOrNull(side2[5])!!
            if (Amphipod.fromSymbolOrNull(side2[7]) != null) positions[18] = Amphipod.fromSymbolOrNull(side2[7])!!
            if (Amphipod.fromSymbolOrNull(side2[9]) != null) positions[19] = Amphipod.fromSymbolOrNull(side2[9])!!
            return BoardState(positions.toMap(), 0)
        }

    }

}
