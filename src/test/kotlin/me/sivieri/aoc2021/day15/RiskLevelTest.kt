package me.sivieri.aoc2021.day15

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class RiskLevelTest {

    @Test
    fun `part 1 example`() {
        val input = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent()
        val riskLevelCalculator = RiskLevelCalculator(input)
        val path = riskLevelCalculator.findLowestRiskPath()
        assertThat(path.second, `is`(40))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent()
        val riskLevelCalculator = RiskLevelCalculator(input, 5)
        println(riskLevelCalculator.matrixToString())
        val path = riskLevelCalculator.findLowestRiskPath()
        println(path.first.joinToString("\n"))
        println(path.first.size)
        println(path.second)
        assertThat(path.second, `is`(315))
    }

    @Test
    fun `extra example`() {
        val input = """
            19999
            19111
            11191
        """.trimIndent()
        val riskLevelCalculator = RiskLevelCalculator(input)
        val path = riskLevelCalculator.findLowestRiskPath()
        println(path.first.joinToString("\n"))
        println(path.first.size)
        println(path.second)
        assertThat(path.second, `is`(8))
    }

}