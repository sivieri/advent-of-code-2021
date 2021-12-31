package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.common.Coordinate3D
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class InstructionTest {

    @Test
    fun `generate cubes`() {
        val instruction = Instruction(InstructionType.ON, 10, 12, 10, 12, 10, 12)
        val cubes = instruction.generateCubes()
        val expected = listOf(
            Coordinate3D(10, 10 , 10),
            Coordinate3D(10, 10, 11),
            Coordinate3D(10, 10, 12),
            Coordinate3D(10, 11, 10),
            Coordinate3D(10, 11, 11),
            Coordinate3D(10, 11, 12),
            Coordinate3D(10, 12, 10),
            Coordinate3D(10, 12, 11),
            Coordinate3D(10, 12, 12),
            Coordinate3D(11, 10, 10),
            Coordinate3D(11, 10, 11),
            Coordinate3D(11, 10, 12),
            Coordinate3D(11, 11, 10),
            Coordinate3D(11, 11, 11),
            Coordinate3D(11, 11, 12),
            Coordinate3D(11, 12, 10),
            Coordinate3D(11, 12, 11),
            Coordinate3D(11, 12, 12),
            Coordinate3D(12, 10, 10),
            Coordinate3D(12, 10, 11),
            Coordinate3D(12, 10, 12),
            Coordinate3D(12, 11, 10),
            Coordinate3D(12, 11, 11),
            Coordinate3D(12, 11, 12),
            Coordinate3D(12, 12, 10),
            Coordinate3D(12, 12, 11),
            Coordinate3D(12, 12, 12)
        )
        assertThat(cubes, containsInAnyOrder(*expected.toTypedArray()))
    }

    @Test
    fun `generate cubes with limit`() {
        val instruction = Instruction(InstructionType.ON, 10, 12, 10, 12, 10, 12)
        val cubes = instruction.generateCubes(limit = 11)
        val expected = listOf(
            Coordinate3D(10, 10 , 10),
            Coordinate3D(10, 10, 11),
            Coordinate3D(10, 11, 10),
            Coordinate3D(10, 11, 11),
            Coordinate3D(11, 10, 10),
            Coordinate3D(11, 10, 11),
            Coordinate3D(11, 11, 10),
            Coordinate3D(11, 11, 11),
        )
        assertThat(cubes, containsInAnyOrder(*expected.toTypedArray()))
    }

}