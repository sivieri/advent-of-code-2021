package me.sivieri.aoc2021.day8

import me.sivieri.aoc2021.*
import me.sivieri.aoc2021.cartesianProductOfThree
import me.sivieri.aoc2021.permutations
import java.lang.Exception

/**
 *   0000
 *  5    1
 *  5    1
 *   6666
 *  4    2
 *  4    2
 *   3333
 */
data class SingleDisplay(
    val uniqueSignalPatterns: List<String>,
    val fourDigitOutput: List<String>
) {

    fun countEasyDigitsInOutput(): Int = fourDigitOutput
        .count { it.length in easyDigits.values }

    fun extractConfiguration(): Digit {
        // element 1 gives us segments 1 and 2
        val segment12 = uniqueSignalPatterns
            .first { it.length == 2 }
            .toSet()
        // element 7 minus element 1 gives us segment 0
        val segment0 = uniqueSignalPatterns
            .first { it.length == 3 }
            .toList()
            .subtract(segment12)
            .first()
        // element 4 minus element 1 gives us segments 5 and 6
        val segment56 = uniqueSignalPatterns
            .first { it.length == 4 }
            .toList()
            .subtract(segment12)
        // the remaining two letters will become segments 3 and 4
        val segment34 = letters
            .toList()
            .subtract(segment12)
            .subtract(segment56)
            .subtract(setOf(segment0))
        val combinations = cartesianProductOfThree(
            segment12.toList().permutations(),
            segment34.toList().permutations(),
            segment56.toList().permutations()
        ).map { listOf(segment0) + it.toList().flatten() }
        val finalCombination = combinations.firstOrNull {
            checkCombination(it)
        } ?: throw Exception("Unable to find a combination that works!")
        return Digit(
            finalCombination.zipWithIndex().toMap(),
            emptyMap()
        )
    }

    private fun checkCombination(
        combination: List<Char>
    ): Boolean {
        val order = combination.zipWithIndex()
        return uniqueSignalPatterns.all { pattern ->
            Digit(
                order.toMap(),
                pattern.toList().associateWith { true }
            ).isValid()
        }
    }

    fun calculateOutputNumber(): Int {
        val digit = extractConfiguration()
        return fourDigitOutput.joinToString("") { number ->
            Digit(
                digit.segments,
                number.toList().associateWith { true }
            ).digit().toString()
        }.toInt()
    }

    companion object {
        private val easyDigits = mapOf(
            1 to 2,
            4 to 4,
            7 to 3,
            8 to 7
        )
        private const val letters = "abcdefg"

        fun fromString(line: String): SingleDisplay {
            val (input, output) = line.split("|")
            return SingleDisplay(
                input.trim().split(" "),
                output.trim().split(" ")
            )
        }

    }
}
