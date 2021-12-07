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
        val result = alignmentSystem.findBestAlignment()
        assertThat(result, `is`(Pair(2, 37)))
    }

}