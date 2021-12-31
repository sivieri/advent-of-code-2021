package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.common.Coordinate3D
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class Region3DTest {

    @Test
    fun `size test`() {
        val region = Region3D(
            Coordinate3D(10, 10, 10),
            Coordinate3D(13, 13, 13),
            InstructionType.ON
        )
        assertThat(region.size(), `is`(27))
    }

    @Test
    fun `full combine`() {
        val region = Region3D(
            Coordinate3D(0, 0, 0),
            Coordinate3D(11, 11, 11),
            InstructionType.ON
        )
        val other = Region3D(
            Coordinate3D(1, 1, 1),
            Coordinate3D(10, 10, 10),
            InstructionType.ON
        )
        val result = region.combine(other)
        assertThat(result.size, `is`(27))
    }

}