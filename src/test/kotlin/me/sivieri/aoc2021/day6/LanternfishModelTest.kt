package me.sivieri.aoc2021.day6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class LanternfishModelTest {

    @Test
    fun `part 1 example 1`() {
        val input = "3,4,3,1,2".split(",").map { it.toInt() }
        val simulator = LanternfishModel(input)
        (1..18).forEach { _ -> simulator.simulateDay() }
        val result = simulator.fishCount()
        assertThat(result, `is`(26))
    }

    @Test
    fun `part 1 example 2`() {
        val input = "3,4,3,1,2".split(",").map { it.toInt() }
        val simulator = LanternfishModel(input)
        (1..80).forEach { _ -> simulator.simulateDay() }
        val result = simulator.fishCount()
        assertThat(result, `is`(5934))
    }

}