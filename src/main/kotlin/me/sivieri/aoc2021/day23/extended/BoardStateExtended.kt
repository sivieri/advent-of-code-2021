package me.sivieri.aoc2021.day23.extended

import me.sivieri.aoc2021.day23.GraphHelper
import org.jgrapht.alg.shortestpath.DijkstraShortestPath

/**
 * #############
 * #...........#
 * ###A#B#C#D###
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #########
 *
 * Position indexes:
 *
 * 01 02 03 04 05 06 07 08 09 10 11
 *       12    13    14    15
 *       16    17    18    19
 *       20    21    22    23
 *       24    25    26    27
 *
 * Rules:
 * 1. move up, down, left or right
 * 2. do not move in or through an occupied space
 * 3. do not stop outside a room
 * 4. do not move in a room different from their final destination
 * 5. do not move in the final destination unless there are similar amphipods
 * 6. do not move remaining in the hallway, unless it is the only amphipod moving
 * 7. move out of your room if there is something different below or it is empty
 */

data class BoardStateExtended(
    val positions: Map<Int, AmphipodExtended>
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
            listOf("  #"),
            listOf(getCell(20)),
            listOf("#"),
            listOf(getCell(21)),
            listOf("#"),
            listOf(getCell(22)),
            listOf("#"),
            listOf(getCell(23)),
            listOf("#  ")
        ).flatten().joinToString("")
        val sixthLine = listOf(
            listOf("  #"),
            listOf(getCell(24)),
            listOf("#"),
            listOf(getCell(25)),
            listOf("#"),
            listOf(getCell(26)),
            listOf("#"),
            listOf(getCell(27)),
            listOf("#  ")
        ).flatten().joinToString("")
        val seventhLine = listOf(
            List(2) { " " },
            List(9) { "#" },
            List(2) { " " }
        ).flatten().joinToString("")
        return listOf(
            firstLine,
            secondLine,
            thirdLine,
            fourthLine,
            fifthLine,
            sixthLine,
            seventhLine
        ).joinToString("\n")
    }

    private fun getCell(index: Int): String =
        positions[index]?.symbol?.toString() ?: "."

    companion object {
        val SOLUTION = fromString(
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        )

        fun fromString(s: String): BoardStateExtended {
            val splitted = s.split("\n", limit = 7)
            val hallway = splitted[1]
            val side1 = splitted[2]
            val side2 = splitted[3]
            val side3 = splitted[4]
            val side4 = splitted[5]
            val positions = mutableMapOf<Int, AmphipodExtended>()
            hallway
                .toList()
                .forEachIndexed { index, c ->
                    val amphipod = AmphipodExtended.fromSymbolOrNull(c)
                    if (amphipod != null) positions[index] = amphipod
                }
            if (AmphipodExtended.fromSymbolOrNull(side1[3]) != null) positions[12] = AmphipodExtended.fromSymbolOrNull(side1[3])!!
            if (AmphipodExtended.fromSymbolOrNull(side1[5]) != null) positions[13] = AmphipodExtended.fromSymbolOrNull(side1[5])!!
            if (AmphipodExtended.fromSymbolOrNull(side1[7]) != null) positions[14] = AmphipodExtended.fromSymbolOrNull(side1[7])!!
            if (AmphipodExtended.fromSymbolOrNull(side1[9]) != null) positions[15] = AmphipodExtended.fromSymbolOrNull(side1[9])!!
            if (AmphipodExtended.fromSymbolOrNull(side2[3]) != null) positions[16] = AmphipodExtended.fromSymbolOrNull(side2[3])!!
            if (AmphipodExtended.fromSymbolOrNull(side2[5]) != null) positions[17] = AmphipodExtended.fromSymbolOrNull(side2[5])!!
            if (AmphipodExtended.fromSymbolOrNull(side2[7]) != null) positions[18] = AmphipodExtended.fromSymbolOrNull(side2[7])!!
            if (AmphipodExtended.fromSymbolOrNull(side2[9]) != null) positions[19] = AmphipodExtended.fromSymbolOrNull(side2[9])!!
            if (AmphipodExtended.fromSymbolOrNull(side3[3]) != null) positions[20] = AmphipodExtended.fromSymbolOrNull(side3[3])!!
            if (AmphipodExtended.fromSymbolOrNull(side3[5]) != null) positions[21] = AmphipodExtended.fromSymbolOrNull(side3[5])!!
            if (AmphipodExtended.fromSymbolOrNull(side3[7]) != null) positions[22] = AmphipodExtended.fromSymbolOrNull(side3[7])!!
            if (AmphipodExtended.fromSymbolOrNull(side3[9]) != null) positions[23] = AmphipodExtended.fromSymbolOrNull(side3[9])!!
            if (AmphipodExtended.fromSymbolOrNull(side4[3]) != null) positions[24] = AmphipodExtended.fromSymbolOrNull(side4[3])!!
            if (AmphipodExtended.fromSymbolOrNull(side4[5]) != null) positions[25] = AmphipodExtended.fromSymbolOrNull(side4[5])!!
            if (AmphipodExtended.fromSymbolOrNull(side4[7]) != null) positions[26] = AmphipodExtended.fromSymbolOrNull(side4[7])!!
            if (AmphipodExtended.fromSymbolOrNull(side4[9]) != null) positions[27] = AmphipodExtended.fromSymbolOrNull(side4[9])!!
            return BoardStateExtended(positions.toMap())
        }
    }
}

data class BoardStateWithCostExtended(
    val boardState: BoardStateExtended,
    val cost: Int
) {

    fun generateValidMoves(): List<BoardStateWithCostExtended> =
        boardState.positions.flatMap { (index, amphipod) ->
            val g = GraphHelper.generateExtendedDirectedGraph(boardState, index) // RULE 1
            val validPositions = searchValidSpace(amphipod, index)
            validPositions
                .mapNotNull { position ->
                    val path = DijkstraShortestPath
                        .findPathBetween(g, BoardCellExtended(index, amphipod), BoardCellExtended(position, null))
                    if (path.vertexList.any { it.amphipod != null && (it.amphipod != amphipod || it.index != index) }) null // RULE 2
                    else {
                        val updatedCost = path
                            .length * amphipod.cost + cost
                        this.copy(
                            boardState = BoardStateExtended(boardState.positions + mapOf(position to amphipod) - index),
                            cost = updatedCost
                        )
                    }
                }
        }

    fun searchValidSpace(
        amphipod: AmphipodExtended,
        index: Int
    ): Set<Int> {
        if (
            amphipod.roomIndexes.contains(index) && // RULE 7
            amphipod.roomIndexes.filter { it > index }.all { boardState.positions[it] == amphipod }
        ) return emptySet()
        val validRooms = searchValidRoomSpace(amphipod)
        val validHallway = searchValidHallwaySpace(index)
        val validPositions = validRooms + validHallway
        return validPositions.filter { it != index }.toSet()
    }

    fun searchValidRoomSpace(amphipod: AmphipodExtended): Set<Int> =
        amphipod
            .roomIndexes // RULE 4
            .let { set ->
                val isRoomOccupied = boardState.positions
                    .filter { it.value != amphipod }
                    .any { set.contains(it.key) } // RULE 5
                if (isRoomOccupied) emptySet()
                else set
            }
            .filter { boardState.positions[it] == null } // RULE 2
            .toSet()

    fun searchValidHallwaySpace(index: Int): Set<Int> =
        (HALLWAY - OUTSIDE_VALUES) // RULE 3
            .let { set ->
                val isHallwayOccupied = boardState.positions
                    .any { HALLWAY.contains(it.key) && it.key != index && HALLWAY.contains(index) } // RULE 6
                if (isHallwayOccupied) emptySet()
                else set
            }
            .filter { boardState.positions[it] == null } // RULE 2
            .toSet()

    fun stringRepresentation(): String = boardState.stringRepresentation()

    fun isSolved() = boardState.isSolved()

    companion object {
        private val HALLWAY = (1..11).toSet()
        private val ROOMS = (12..27).toSet()
        private val OUTSIDE_VALUES = setOf(3, 5, 7, 9)

        fun fromString(s: String, cost: Int = 0): BoardStateWithCostExtended {
            return BoardStateWithCostExtended(BoardStateExtended.fromString(s), cost)
        }

    }

}
