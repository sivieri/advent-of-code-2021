package me.sivieri.aoc2021.day6

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class LanternfishOptimizedModelTest {

    @Test
    fun `part 2 example`() {
        val input = "3,4,3,1,2".split(",").map { it.toInt() }
        val simulator = LanternfishOptimizedModel(input)
        val result = simulator.simulate(256)
        MatcherAssert.assertThat(result, Matchers.`is`(26984457539))
    }

}