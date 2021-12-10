package me.sivieri.aoc2021.day9

import me.sivieri.aoc2021.common.Coordinate2D
import me.sivieri.aoc2021.multiplyBy
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class HeightmapTest {

    @Test
    fun `part 1 low points`() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")
        val heightmap = Heightmap(input)
        val result = heightmap.findLowPoints()
        val expected = listOf(
            Pair(Coordinate2D(0, 1), 1),
            Pair(Coordinate2D(0, 9), 0),
            Pair(Coordinate2D(2, 2,), 5),
            Pair(Coordinate2D(4, 6), 5)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `part 1 risk level`() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")
        val heightmap = Heightmap(input)
        val result = heightmap.calculateRiskLevel()
        assertThat(result, `is`(15))
    }

    @Test
    fun `part 2 all basins`() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")
        val heightmap = Heightmap(input)
        val result = heightmap
            .findBasins()
        println(heightmap.basinsToString(result))
        assertThat(result.size, `is`(4))
    }

    @Test
    fun `part 2 largest basins`() {
        val input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent().split("\n")
        val heightmap = Heightmap(input)
        val result = heightmap.findLargestBasins().multiplyBy { it.size.toLong() }
        assertThat(result, `is`(1134L))
    }

}