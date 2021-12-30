package me.sivieri.aoc2021.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import kotlin.math.exp

class ImageTest {

    @Test
    fun `count light test`() {
        val s = """
            ...............
            ...............
            ...............
            ..........#....
            ....#..#.#.....
            ...#.#...###...
            ...#...##.#....
            ...#.....#.#...
            ....#.#####....
            .....#.#####...
            ......##.##....
            .......###.....
            ...............
            ...............
            ...............
        """.trimIndent()
        val image = Image.fromString(s)
        assertThat(image.countLight(), `is`(35))
    }

    @Test
    fun `enlarge test`() {
        val s = """
            #..#.
            #....
            ##..#
            ..#..
            ..###
        """.trimIndent()
        val image = Image.fromString(s)
        val expectedS = """
            .........
            .........
            ..#..#...
            ..#......
            ..##..#..
            ....#....
            ....###..
            .........
            .........
        """.trimIndent()
        val expected = Image.fromString(expectedS)
        assertThat(image.enlarge(2), `is`(expected))
    }

    @Test
    fun `cut test`() {
        val s = """
            .........
            .........
            ..#..#...
            ..#......
            ..##..#..
            ....#....
            ....###..
            .........
            .........
        """.trimIndent()
        val image = Image.fromString(s)
        val expectedS = """
            #..#.
            #....
            ##..#
            ..#..
            ..###
        """.trimIndent()
        val expected = Image.fromString(expectedS)
        assertThat(image.cut(2), `is`(expected))
    }

    @Test
    fun `enhance test`() {
        val s = """
            #..#.
            #....
            ##..#
            ..#..
            ..###
        """.trimIndent()
        val algorithm = """
            ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##
            #..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###
            .######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#.
            .#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#.....
            .#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#..
            ...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.....
            ..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#
        """.trimIndent()
            .split("\n")
            .joinToString("")
            .toList()
            .toTypedArray()
        val image = Image.fromString(s)
        val expectedS = """
            ..#.#
            #.#..
            ###..
            #..##
            .##..
        """.trimIndent()
        val expected = Image.fromString(expectedS)
        val result = image.enhance(algorithm)
        println(expected.stringRepresentation())
        println()
        println(result.stringRepresentation())
        assertThat(result, `is`(expected))
    }

}