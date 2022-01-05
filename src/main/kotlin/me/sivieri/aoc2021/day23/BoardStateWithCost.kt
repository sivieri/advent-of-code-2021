package me.sivieri.aoc2021.day23

import org.jgrapht.alg.shortestpath.DijkstraShortestPath

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
data class BoardStateWithCost(
    val positions: Map<Int, Amphipod>,
    val cost: Int
) {

    fun toBoardState() = BoardState(positions)

    fun generateValidMoves(): List<BoardStateWithCost> =
        positions.flatMap { (index, amphipod) ->
            val g = GraphHelper.generateDirectedGraph(this, index) // RULE 1
            val validPositions = searchValidSpace(amphipod, index)
            validPositions
                .mapNotNull { position ->
                    val path = DijkstraShortestPath
                        .findPathBetween(g, BoardCell(index, amphipod), BoardCell(position, null))
                    if (path.vertexList.any { it.amphipod != null && it.amphipod != amphipod }) null // RULE 2
                    else {
                        val updatedCost = path
                            .length * amphipod.cost + cost
                        this.copy(
                            positions = positions + mapOf(position to amphipod) - index,
                            cost = updatedCost
                        )
                    }
                }
        }

    fun searchValidSpace(
        amphipod: Amphipod,
        index: Int
    ): Set<Int> {
        if (
            amphipod.roomIndexes.contains(index) && // RULE 7
            positions[amphipod.roomIndexes.maxOrNull()!!] == amphipod
        ) return emptySet()
        val validRooms = searchValidRoomSpace(amphipod)
        val validHallway = searchValidHallwaySpace(index)
        val validPositions = validRooms + validHallway
        return validPositions.filter { it != index }.toSet()
    }

    fun searchValidRoomSpace(amphipod: Amphipod): Set<Int> =
        amphipod
            .roomIndexes // RULE 4
            .let { set ->
                val isRoomOccupied = positions
                    .filter { it.value != amphipod }
                    .any { set.contains(it.key) } // RULE 5
                if (isRoomOccupied) emptySet()
                else set
            }
            .filter { positions[it] == null } // RULE 2
            .toSet()

    fun searchValidHallwaySpace(index: Int): Set<Int> =
        (HALLWAY - OUTSIDE_VALUES) // RULE 3
            .let { set ->
                val isHallwayOccupied = positions
                    .any { HALLWAY.contains(it.key) && it.key != index && HALLWAY.contains(index) } // RULE 6
                if (isHallwayOccupied) emptySet()
                else set
            }
            .filter { positions[it] == null } // RULE 2
            .toSet()

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
        private val HALLWAY = (1..11).toSet()
        private val ROOMS = (12..19).toSet()
        private val OUTSIDE_VALUES = setOf(3, 5, 7, 9)
        private val SOLUTION = fromString(
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        )

        fun fromString(s: String, cost: Int = 0): BoardStateWithCost {
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
            return BoardStateWithCost(positions.toMap(), cost)
        }

    }

}
