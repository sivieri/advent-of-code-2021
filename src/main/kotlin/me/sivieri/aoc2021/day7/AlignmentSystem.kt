package me.sivieri.aoc2021.day7

import kotlin.math.abs

class AlignmentSystem(
    private val startingPositions: List<Int>
) {

    fun findBestAlignmentAtConstantConsumption(): Pair<Int, Int> {
        val min = startingPositions.minOrNull()!!
        val max = startingPositions.maxOrNull()!!
        val positions = (min..max)
            .map { index ->
                index to startingPositions.sumOf { abs(it - index) }
            }
        return positions
            .minByOrNull { it.second }!!
    }

    fun findBestAlignmentAtIncreasingConsumption(): Pair<Int, Int> {
        val min = startingPositions.minOrNull()!!
        val max = startingPositions.maxOrNull()!!
        val positions = (min..max)
            .map { index ->
                index to startingPositions.sumOf { (1..abs(it - index)).sum() }
            }
        return positions
            .minByOrNull { it.second }!!
    }

}