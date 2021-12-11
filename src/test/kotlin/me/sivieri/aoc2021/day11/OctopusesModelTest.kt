package me.sivieri.aoc2021.day11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class OctopusesModelTest {

    @Test
    fun `small example step status`() {
        val input = """
            11111
            19991
            19191
            19991
            11111
        """.trimIndent().split("\n")
        val model = OctopusesModel(input)
        val flashes = model.performStep()
        val expected = """
            34543
            40004
            50005
            40004
            34543
        """.trimIndent()
        assertThat(model.stringRepresentation(), `is`(expected))
        assertThat(flashes, `is`(9))
    }

    @Test
    fun `large example step status`() {
        val input = """
            6594254334
            3856965822
            6375667284
            7252447257
            7468496589
            5278635756
            3287952832
            7993992245
            5957959665
            6394862637
        """.trimIndent().split("\n")
        val model = OctopusesModel(input)
        val flashes = model.performStep()
        val expected = """
            8807476555
            5089087054
            8597889608
            8485769600
            8700908800
            6600088989
            6800005943
            0000007456
            9000000876
            8700006848
        """.trimIndent()
        assertThat(model.stringRepresentation(), `is`(expected))
        assertThat(flashes, `is`(35))
    }

    @Test
    fun `large example flashes after 100 iterations`() {
        val input = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent().split("\n")
        val model = OctopusesModel(input)
        val flashes = model.performNSteps(100)
        assertThat(flashes, `is`(1656))
    }

    @Test
    fun `large example simultaneous step`() {
        val input = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent().split("\n")
        val model = OctopusesModel(input)
        val step = model.findSimultaneousStep()
        assertThat(step, `is`(195))
    }

}