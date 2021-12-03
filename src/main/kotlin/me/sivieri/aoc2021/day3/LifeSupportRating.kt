package me.sivieri.aoc2021.day3

data class LifeSupportRating(
    val oxygenGeneratorRating: Int,
    val co2ScrubberRating: Int
) {
    fun calculateLifeSupportRating() = oxygenGeneratorRating * co2ScrubberRating
}
