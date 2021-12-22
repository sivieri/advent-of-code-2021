package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.common.Coordinate3D
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class ScannerTest {

    @Test
    fun `parsing test`() {
        val input = """
            --- scanner 0 ---
            -1,-1,1
            -2,-2,2
            -3,-3,3
            -2,-3,1
            5,6,-4
            8,0,7
        """.trimIndent()
        val scanner = Scanner.fromString(input)
        val expected = Scanner(
            0,
            listOf(
                Coordinate3D(-1, -1, 1),
                Coordinate3D(-2, -2, 2),
                Coordinate3D(-3, -3, 3),
                Coordinate3D(-2, -3, 1),
                Coordinate3D(5, 6, -4),
                Coordinate3D(8, 0, 7)
            )
        )
        assertThat(scanner, `is`(expected))
    }

}