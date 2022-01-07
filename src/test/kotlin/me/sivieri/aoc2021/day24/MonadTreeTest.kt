package me.sivieri.aoc2021.day24

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class MonadTreeTest {

    @Test
    fun `example 1`() {
        val input = """
            inp w
            add z w
            mod z 2
            div w 2
            add y w
            mod y 2
            div w 2
            add x w
            mod x 2
            div w 2
            mod w 2
        """.trimIndent().split("\n")
        val tree = MonadTree(input)
        val result = tree.searchMax()
        assertThat(result, `is`(8))
    }

}