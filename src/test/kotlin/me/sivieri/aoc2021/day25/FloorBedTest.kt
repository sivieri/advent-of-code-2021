package me.sivieri.aoc2021.day25

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class FloorBedTest {

    @Test
    fun `single step test 1`() {
        val start = """
            ...>...
            .......
            ......>
            v.....>
            ......>
            .......
            ..vvv..
        """.trimIndent()
            .split("\n")
            .map { it.toList().toTypedArray() }
            .toTypedArray()
        val end = """
            ..vv>..
            .......
            >......
            v.....>
            >......
            .......
            ....v..
        """.trimIndent()
            .split("\n")
            .map { it.toList().toTypedArray() }
            .toTypedArray()
        val result = FloorBed.calculateUpdate(start)
        assertThat(result.first, `is`(end))
    }

    @Test
    fun `part 1 example`() {
        val input = """
            v...>>.vv>
            .vv>>.vv..
            >>.>v>...v
            >>v>>.>.v.
            v>v.vv.v..
            >.>>..v...
            .vv..>.>v.
            v.v..>>v.v
            ....v..v.>
        """.trimIndent()
        val bed = FloorBed(input)
        val steps = bed.evolve()
        assertThat(steps, `is`(58))
    }

}