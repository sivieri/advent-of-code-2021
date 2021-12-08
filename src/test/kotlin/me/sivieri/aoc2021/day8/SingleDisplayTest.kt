package me.sivieri.aoc2021.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class SingleDisplayTest {

    @Test
    fun `calculate example configuration`() {
        val input = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
        val singleDisplay = SingleDisplay.fromString(input)
        val result = singleDisplay.extractConfiguration()
        val expected = Digit(
            mapOf(
                0 to 'd',
                1 to 'a',
                2 to 'b',
                3 to 'c',
                4 to 'g',
                5 to 'e',
                6 to 'f'
            ),
            emptyMap()
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `calculate example output number`() {
        val input = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
        val singleDisplay = SingleDisplay.fromString(input)
        val result = singleDisplay.calculateOutputNumber()
        assertThat(result, `is`(5353))
    }

}