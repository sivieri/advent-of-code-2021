package me.sivieri.aoc2021.day5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class OceanFloorTest {

    @Test
    fun `part 1 example`() {
        val lines = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent()
            .split("\n")
            .map { Line.parseString(it) }
        val maxX = lines.maxOf { maxOf(it.start.x, it.end.x) }
        val maxY = lines.maxOf { maxOf(it.start.y, it.end.y) }
        val oceanFloor = OceanFloor(maxX, maxY)
        oceanFloor.calculateVents(lines, false)
        val result = oceanFloor.countHighConcentration()
        assertThat(result, `is`(5))
    }

    @Test
    fun `part 2 example`() {
        val lines = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent()
            .split("\n")
            .map { Line.parseString(it) }
        val maxX = lines.maxOf { maxOf(it.start.x, it.end.x) }
        val maxY = lines.maxOf { maxOf(it.start.y, it.end.y) }
        val oceanFloor = OceanFloor(maxX, maxY)
        oceanFloor.calculateVents(lines, true)
        val result = oceanFloor.countHighConcentration()
        assertThat(result, `is`(12))
    }

}