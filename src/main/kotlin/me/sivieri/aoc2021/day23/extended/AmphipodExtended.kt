package me.sivieri.aoc2021.day23.extended

enum class AmphipodExtended(
    val symbol: Char,
    val cost: Int,
    val roomIndexes: Set<Int>
) {
    AMBER('A', 1, setOf(12, 16, 20, 24)),
    BRONZE('B', 10, setOf(13, 17, 21, 25)),
    COPPER('C', 100, setOf(14, 18, 22, 26)),
    DESERT('D', 1000, setOf(15, 19, 23, 27));

    companion object {
        fun fromSymbolOrNull(c: Char): AmphipodExtended? = when (c) {
            AMBER.symbol -> AMBER
            BRONZE.symbol -> BRONZE
            COPPER.symbol -> COPPER
            DESERT.symbol -> DESERT
            else -> null
        }
    }
}
