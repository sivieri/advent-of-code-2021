package me.sivieri.aoc2021.day23

enum class Amphipod(
    val symbol: Char,
    val cost: Int,
    val roomIndexes: List<Int>
) {
    AMBER('A', 1, listOf(12, 16)),
    BRONZE('B', 10, listOf(13, 17)),
    COPPER('C', 100, listOf(14, 18)),
    DESERT('D', 1000, listOf(15, 19));

    companion object {
        fun fromSymbolOrNull(c: Char): Amphipod? = when (c) {
            AMBER.symbol -> AMBER
            BRONZE.symbol -> BRONZE
            COPPER.symbol -> COPPER
            DESERT.symbol -> DESERT
            else -> null
        }
    }
}
