package me.sivieri.aoc2021.day5

import me.sivieri.aoc2021.common.Coordinate2D
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.empty
import org.junit.Assert
import org.junit.Test

class LineTest {

    @Test
    fun `parse string`() {
        val l = "0,4 -> 2,4"
        val result = Line.parseString(l)
        val expected = Line(Coordinate2D(0, 4), Coordinate2D(2, 4))
        assertThat(result, `is`(expected))
    }

    @Test
    fun `get horizontal line`() {
        val line = Line(Coordinate2D(0, 4), Coordinate2D(2, 4))
        val result = line.getFullLineCoordinates(false)
        val expected = listOf(
            Coordinate2D(0, 4),
            Coordinate2D(1, 4),
            Coordinate2D(2, 4)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `get horizontal line from higher`() {
        val line = Line(Coordinate2D(2, 4), Coordinate2D(0, 4))
        val result = line.getFullLineCoordinates(false)
        val expected = listOf(
            Coordinate2D(0, 4),
            Coordinate2D(1, 4),
            Coordinate2D(2, 4)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `get vertical line`() {
        val line = Line(Coordinate2D(0, 4), Coordinate2D(0, 6))
        val result = line.getFullLineCoordinates(false)
        val expected = listOf(
            Coordinate2D(0, 4),
            Coordinate2D(0, 5),
            Coordinate2D(0, 6)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `get vertical line from higher`() {
        val line = Line(Coordinate2D(0, 6), Coordinate2D(0, 4))
        val result = line.getFullLineCoordinates(false)
        val expected = listOf(
            Coordinate2D(0, 4),
            Coordinate2D(0, 5),
            Coordinate2D(0, 6)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `diagonal returns empty if not explicitly asked for`() {
        val line = Line(Coordinate2D(0, 4), Coordinate2D(2, 6))
        val result = line.getFullLineCoordinates(false)
        assertThat(result, empty())
    }

}