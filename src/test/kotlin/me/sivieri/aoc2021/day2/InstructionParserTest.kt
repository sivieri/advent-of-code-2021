package me.sivieri.aoc2021.day2

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class InstructionParserTest {

    @Test
    fun `part 1 example`() {
        val instructions = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """.trimIndent()
        val lines = instructions.split("\n")
        val parser = InstructionParser()
        val finalPosition = parser.parse(lines)
        val result = finalPosition.horizontalPosition * finalPosition.depth
        assertThat(result, `is`(150))
    }

    @Test
    fun `part 2 example with aim`() {
        val instructions = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """.trimIndent()
        val lines = instructions.split("\n")
        val parser = InstructionParser()
        val finalPosition = parser.parseWithAim(lines)
        val result = finalPosition.horizontalPosition * finalPosition.depth
        assertThat(result, `is`(900))
    }

}