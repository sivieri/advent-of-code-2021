package me.sivieri.aoc2021.day7

import me.sivieri.aoc2021.toIntList
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class AlignmentSystemTest {

    @Test
    fun `part 1 example`() {
        val input = "16,1,2,0,4,2,7,1,2,14".toIntList()
        val alignmentSystem = AlignmentSystem(input)
        val result = alignmentSystem.findBestAlignmentAtConstantConsumption()
        assertThat(result, `is`(Pair(2, 37)))
    }

    @Test
    fun `part 2 example`() {
        val input = "16,1,2,0,4,2,7,1,2,14".toIntList()
        val alignmentSystem = AlignmentSystem(input)
        val result = alignmentSystem.findBestAlignmentAtIncreasingConsumption()
        assertThat(result, `is`(Pair(5, 168)))
    }

}