package me.sivieri.aoc2021.day12

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
    }
}

enum class CaveSize {
    BIG, SMALL;
}
