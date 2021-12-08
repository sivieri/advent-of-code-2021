package me.sivieri.aoc2021.day8

data class SingleDisplay(
    val uniqueSignalPatterns: List<String>,
    val fourDigitOutput: List<String>
) {

    fun countEasyDigitsInOutput(): Int = fourDigitOutput
        .count { it.length in easyDigits }

    companion object {
        private val digitToSegments = mapOf(
            0 to 6,
            1 to 2,
            2 to 5,
            3 to 5,
            4 to 4,
            5 to 5,
            6 to 6,
            7 to 3,
            8 to 7,
            9 to 6
        )
        private val easyDigits = digitToSegments
            .entries
            .groupingBy { it.value }
            .eachCount()
            .filter { it.value == 1 }
            .map { it.key }
    }
}