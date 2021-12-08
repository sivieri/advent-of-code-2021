package me.sivieri.aoc2021.day8

class FullDisplay(
    lines: List<String>
) {

    private val displays = lines
        .map { line ->
            SingleDisplay.fromString(line)
        }

    fun countEasyDigitsInOutput(): Int = displays.sumOf { it.countEasyDigitsInOutput() }

    fun sumTotal(): Int = displays.sumOf { it.calculateOutputNumber() }

}
