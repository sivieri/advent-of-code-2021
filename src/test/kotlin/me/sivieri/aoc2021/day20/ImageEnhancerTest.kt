package me.sivieri.aoc2021.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class ImageEnhancerTest {

    @Test
    fun `part 1 example`() {
        val s = """
            #..#.
            #....
            ##..#
            ..#..
            ..###
        """.trimIndent()
        val input = """
            ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##
            #..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###
            .######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#.
            .#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#.....
            .#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#..
            ...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.....
            ..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#
        """.trimIndent()
            .split("\n")
            .joinToString("") + "\n\n" + s
        val enhancer = ImageEnhancer(input)
        val result = enhancer.enhance(2)
        assertThat(result.countLight(), `is`(35))
    }

}