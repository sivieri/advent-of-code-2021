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
    val a: Pair<Int, Int>,
    val b: Pair<Int, Int>,
    val c: Pair<Int, Int>,
    val d: Pair<Int, Int>
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

    private fun getCell(index: Int): String {
        TODO("Not yet implemented")
    }

}
