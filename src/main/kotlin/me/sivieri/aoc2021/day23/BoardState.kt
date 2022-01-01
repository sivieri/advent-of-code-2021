package me.sivieri.aoc2021.day23

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

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
    val a: Pair<Int, Int>,
    val b: Pair<Int, Int>,
    val c: Pair<Int, Int>,
    val d: Pair<Int, Int>,
    val cost: Int
) {

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
        when {
            a.first == index -> "A"
            a.second == index -> "A"
            b.first == index -> "B"
            b.second == index -> "B"
            c.first == index -> "C"
            c.second == index -> "C"
            d.first == index -> "D"
            d.second == index -> "D"
            else -> "."
        }

    fun isSolved() = this == SOLUTION

    companion object {
        private val SOLUTION = fromString(
            "#############\n" +
            "#...........#\n" +
            "###A#B#C#D###\n" +
            "  #A#B#C#D#  \n" +
            "  #########  "
        )

        fun fromString(s: String): BoardState {
            val (_, hallway, side1, side2, _) = s.split("\n", limit = 5)
            val positions = mutableMapOf<Int, String>()
            hallway
                .toList()
                .forEachIndexed { index, c ->
                    when (c) {
                        'A' -> positions[index] = "A"
                        'B' -> positions[index] = "B"
                        'C' -> positions[index] = "C"
                        'D' -> positions[index] = "D"
                        else -> { }
                    }
                }
            if (side1[3] != '.') positions[12] = side1[3].toString()
            if (side1[5] != '.') positions[13] = side1[5].toString()
            if (side1[7] != '.') positions[14] = side1[7].toString()
            if (side1[9] != '.') positions[15] = side1[9].toString()
            if (side2[3] != '.') positions[16] = side2[3].toString()
            if (side2[5] != '.') positions[17] = side2[5].toString()
            if (side2[7] != '.') positions[18] = side2[7].toString()
            if (side2[9] != '.') positions[19] = side2[9].toString()
            val a = positions
                .filter { it.value == "A" }
                .keys
                .toList()
                .let { Pair(it[0], it[1]) }
            val b = positions
                .filter { it.value == "B" }
                .keys
                .toList()
                .let { Pair(it[0], it[1]) }
            val c = positions
                .filter { it.value == "C" }
                .keys
                .toList()
                .let { Pair(it[0], it[1]) }
            val d = positions
                .filter { it.value == "D" }
                .keys
                .toList()
                .let { Pair(it[0], it[1]) }
            return BoardState(a, b, c, d, 0)
        }

        fun fromGraph(graph: Graph<BoardCell, DefaultEdge>, cost: Int): BoardState {
            val amphipods = graph
                .vertexSet()
                .filter { it.amphipod != null }
            val a = amphipods
                .filter { it.amphipod == Amphipod.AMBER }
                .map { it.index }
                .let { Pair(it[0], it[1]) }
            val b = amphipods
                .filter { it.amphipod == Amphipod.BRONZE }
                .map { it.index }
                .let { Pair(it[0], it[1]) }
            val c = amphipods
                .filter { it.amphipod == Amphipod.COPPER }
                .map { it.index }
                .let { Pair(it[0], it[1]) }
            val d = amphipods
                .filter { it.amphipod == Amphipod.DESERT }
                .map { it.index }
                .let { Pair(it[0], it[1]) }
            return BoardState(a, b, c, d, cost)
        }

    }

}
