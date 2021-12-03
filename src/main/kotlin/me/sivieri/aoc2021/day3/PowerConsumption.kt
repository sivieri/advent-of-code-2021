package me.sivieri.aoc2021.day3

data class PowerConsumption(
    val gammaRate: Int,
    val epsilonRate: Int
) {
    fun calculatePowerConsumption() = gammaRate * epsilonRate
}
