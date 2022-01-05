package me.sivieri.aoc2021.day23

enum class Amphipod(
    val symbol: Char,
    val cost: Int,
    val roomIndexes: Set<Int>
) {
    AMBER('A', 1, setOf(12, 16)),
    BRONZE('B', 10, setOf(13, 17)),
    COPPER('C', 100, setOf(14, 18)),
    DESERT('D', 1000, setOf(15, 19));

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
