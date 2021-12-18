package me.sivieri.aoc2021.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TreeNumberPairTest {

    @Test
    fun `parsing test 1`() {
        val input = "[1,2]"
        val tree = TreeNumberPair.treeFromString(input)
        val expected = TreePair(TreeNumber(1), TreeNumber(2))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `parsing test 2`() {
        val input = "[[1,2],3]"
        val tree = TreeNumberPair.treeFromString(input)
        val expected = TreePair(TreePair(TreeNumber(1), TreeNumber(2)), TreeNumber(3))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `parsing test 3`() {
        val input = "[9,[8,7]]"
        val tree = TreeNumberPair.treeFromString(input)
        val expected = TreePair(TreeNumber(9), TreePair(TreeNumber(8), TreeNumber(7)))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `parsing test 4`() {
        val input = "[[1,9],[8,5]]"
        val tree = TreeNumberPair.treeFromString(input)
        val expected = TreePair(TreePair(TreeNumber(1), TreeNumber(9)), TreePair(TreeNumber(8), TreeNumber(5)))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `simple addition`() {
        val input1 = "[1,2]"
        val tree1 = TreeNumberPair.treeFromString(input1)
        val input2 = "[[3,4],5]"
        val tree2 = TreeNumberPair.treeFromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.treeFromString("[[1,2],[[3,4],5]]")
        assertThat(result, `is`(expected))
    }

    @Test
    fun `number split test 1`() {
        val input = TreeNumber(10)
        val result = input.split()
        assertThat(result, `is`(TreePair(TreeNumber(5), TreeNumber(5))))
    }

    @Test
    fun `number split test 2`() {
        val input = TreeNumber(11)
        val result = input.split()
        assertThat(result, `is`(TreePair(TreeNumber(5), TreeNumber(6))))
    }

    @Test
    fun `number split test 3`() {
        val input = TreeNumber(12)
        val result = input.split()
        assertThat(result, `is`(TreePair(TreeNumber(6), TreeNumber(6))))
    }

}