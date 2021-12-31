package me.sivieri.aoc2021.day22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class InstructionParserTest {

    @Test
    fun `parse lines`() {
        val input = """
            on x=10..12,y=10..12,z=10..12
            on x=11..13,y=11..13,z=11..13
            off x=9..11,y=9..11,z=9..11
            on x=10..10,y=10..10,z=10..10
        """.trimIndent().split("\n")
        val parser = InstructionParser()
        val instructions = parser.parse(input)
        val expected = listOf(
            Instruction(InstructionType.ON, 10, 12, 10, 12, 10, 12),
            Instruction(InstructionType.ON, 11, 13, 11, 13, 11, 13),
            Instruction(InstructionType.OFF, 9, 11, 9, 11, 9, 11),
            Instruction(InstructionType.ON, 10, 10, 10, 10, 10, 10),
        )
        assertThat(instructions, containsInAnyOrder(*expected.toTypedArray()))
    }

    @Test
    fun `parse lines with limit`() {
        val input = """
            on x=10..12,y=10..12,z=10..12
            on x=11..13,y=11..13,z=11..13
            off x=9..11,y=9..11,z=9..11
            on x=10..10,y=10..10,z=10..10
        """.trimIndent().split("\n")
        val parser = InstructionParser()
        val instructions = parser.parse(input, limit = 12)
        val expected = listOf(
            Instruction(InstructionType.ON, 10, 12, 10, 12, 10, 12),
            Instruction(InstructionType.OFF, 9, 11, 9, 11, 9, 11),
            Instruction(InstructionType.ON, 10, 10, 10, 10, 10, 10),
        )
        assertThat(instructions, containsInAnyOrder(*expected.toTypedArray()))
    }

}