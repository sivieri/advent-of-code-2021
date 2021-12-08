package me.sivieri.aoc2021

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun `test permutations of ints`() {
        val result = listOf(1, 2, 3).permutations()
        val expected = listOf(
            listOf(1, 2, 3),
            listOf(2, 1, 3),
            listOf(3, 1, 2),
            listOf(1, 3, 2),
            listOf(2, 3, 1),
            listOf(3, 2, 1)
        )
        assertThat(result, containsInAnyOrder(*expected.toTypedArray()))
    }

    @Test
    fun `test permutations of chars`() {
        val result = "abc".toCharArray().toList().permutations()
        val expected = listOf(
            listOf('a', 'b', 'c'),
            listOf('b', 'a', 'c'),
            listOf('c', 'a', 'b'),
            listOf('a', 'c', 'b'),
            listOf('b', 'c', 'a'),
            listOf('c', 'b', 'a')
        )
        assertThat(result, containsInAnyOrder(*expected.toTypedArray()))
    }

    @Test
    fun `cartesian product`() {
        val a = listOf(1, 2, 3)
        val b = listOf(true, false)
        val result = cartesianProduct(a, b)
        val expected = listOf(
            Pair(1, true),
            Pair(1, false),
            Pair(2, true),
            Pair(2, false),
            Pair(3, true),
            Pair(3, false)
        )
        assertThat(result, containsInAnyOrder(*expected.toTypedArray()))
    }

}