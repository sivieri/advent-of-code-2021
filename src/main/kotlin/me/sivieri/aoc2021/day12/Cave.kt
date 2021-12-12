package me.sivieri.aoc2021.day12

import me.sivieri.aoc2021.isLowerCase

data class Cave(
    val name: String,
    val size: CaveSize
) {
    fun isStart() = name == START_VERTEX

    fun isEnd() = name == END_VERTEX

    fun isSmall() = size == CaveSize.SMALL

    companion object {
        private const val START_VERTEX = "start"
        private const val END_VERTEX = "end"

        fun fromName(name: String): Cave {
            val size = when {
                name == START_VERTEX -> CaveSize.LIMIT
                name == END_VERTEX -> CaveSize.LIMIT
                name.isLowerCase() -> CaveSize.SMALL
                else -> CaveSize.BIG
            }
            return Cave(name, size)
        }
    }
}

enum class CaveSize {
    BIG, SMALL, LIMIT;
}
