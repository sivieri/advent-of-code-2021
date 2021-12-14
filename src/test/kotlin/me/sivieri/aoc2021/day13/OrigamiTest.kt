package me.sivieri.aoc2021.day13

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class OrigamiTest {

    @Test
    fun `part 1 example`() {
        val input = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """.trimIndent()
        val origami = Origami(input)
        origami.applyFold(FoldInstruction(CoordinateName.X, 7))
        println(origami.getBoard())
        assertThat(origami.countDots(), `is`(17))
    }

    @Test
    fun `part 1 example 2`() {
        val input = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5
        """.trimIndent()
        val origami = Origami(input)
        origami.applyFold(FoldInstruction(CoordinateName.X, 7))
        origami.applyFold(FoldInstruction(CoordinateName.Y, 5))
        println(origami.getBoard())
        assertThat(origami.countDots(), `is`(16))
    }

}