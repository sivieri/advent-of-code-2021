package me.sivieri.aoc2021.day3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class BinaryProcessorTest {

    @Test
    fun `part 1 power consumption`() {
        val input = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """.trimIndent()
            .split("\n")
        val processor = BinaryProcessor()
        val powerConsumption = processor.calculatePowerConsumption(input)
        assertThat(powerConsumption.calculatePowerConsumption(), equalTo(198))
    }

}