package me.sivieri.aoc2021.day8

/**
 *   0000
 *  5    1
 *  5    1
 *   6666
 *  4    2
 *  4    2
 *   3333
 */
data class Digit(
    val segments: Map<Int, Char>,
    val status: Map<Char, Boolean>
) {

    fun digit(): Int {
        val onSegments = status
            .filter { it.value }
            .keys
        val onPositions = segments
            .filter { it.value in onSegments }
            .keys
        return digitByPositions[onPositions] ?: throw IllegalStateException("Invalid combination of segments: ${toString()}")
    }

    fun isValid(): Boolean = try {
        digit()
        true
    }
    catch (e: Exception) {
        false
    }

    companion object {
        private val positionsByDigit = mapOf(
            0 to setOf(0, 1, 2, 3, 4, 5),
            1 to setOf(1, 2),
            2 to setOf(0, 1, 3, 4, 6),
            3 to setOf(0, 1, 2, 3, 6),
            4 to setOf(1, 2, 5, 6),
            5 to setOf(0, 2, 3, 5, 6),
            6 to setOf(0, 2, 3, 4, 5, 6),
            7 to setOf(0, 1, 2),
            8 to setOf(0, 1, 2, 3, 4, 5, 6),
            9 to setOf(0, 1, 2, 3, 5, 6)
        )
        private val digitByPositions = mapOf(
            setOf(0, 1, 2, 3, 4, 5) to 0,
            setOf(1, 2) to 1,
            setOf(0, 1, 3, 4, 6) to 2,
            setOf(0, 1, 2, 3, 6) to 3,
            setOf(1, 2, 5, 6) to 4,
            setOf(0, 2, 3, 5, 6) to 5,
            setOf(0, 2, 3, 4, 5, 6) to 6,
            setOf(0, 1, 2) to 7,
            setOf(0, 1, 2, 3, 4, 5, 6) to 8,
            setOf(0, 1, 2, 3, 5, 6) to 9
        )

    }

}
