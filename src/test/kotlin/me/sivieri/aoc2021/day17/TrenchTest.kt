package me.sivieri.aoc2021.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TrenchTest {

    @Test
    fun `part 1 example`() {
        val trench = Trench(20, 30, -10, -5)
        val result = trench.findMaxY()
        assertThat(result, `is`(45))
    }

}