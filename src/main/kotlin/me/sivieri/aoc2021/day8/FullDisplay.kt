package me.sivieri.aoc2021.day8

class FullDisplay(
    lines: List<String>
) {

    private val displays = lines
        .map { line ->
            val (input, output) = line.split("|")
            SingleDisplay(
                input.trim().split(" "),
                output.trim().split(" ")
            )
        }

    fun countEasyDigitsInOutput(): Int = displays.sumOf { it.countEasyDigitsInOutput() }

}