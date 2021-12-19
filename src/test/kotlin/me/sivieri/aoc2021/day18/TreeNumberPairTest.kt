package me.sivieri.aoc2021.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TreeNumberPairTest {

    @Test
    fun `parsing test 1`() {
        val input = "[1,2]"
        val tree = TreeNumberPair.fromString(input)
        val expected = TreePair(TreeNumber(1), TreeNumber(2))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `parsing test 2`() {
        val input = "[[1,2],3]"
        val tree = TreeNumberPair.fromString(input)
        val expected = TreePair(TreePair(TreeNumber(1), TreeNumber(2)), TreeNumber(3))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `parsing test 3`() {
        val input = "[9,[8,7]]"
        val tree = TreeNumberPair.fromString(input)
        val expected = TreePair(TreeNumber(9), TreePair(TreeNumber(8), TreeNumber(7)))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `parsing test 4`() {
        val input = "[[1,9],[8,5]]"
        val tree = TreeNumberPair.fromString(input)
        val expected = TreePair(TreePair(TreeNumber(1), TreeNumber(9)), TreePair(TreeNumber(8), TreeNumber(5)))
        assertThat(tree, `is`(expected))
    }

    @Test
    fun `simple addition`() {
        val input1 = "[1,2]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[[3,4],5]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[1,2],[[3,4],5]]")
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

    @Test
    fun `tree explode test 1`() {
        val result = TreeNumberPair
            .fromString("[[[[[9,8],1],2],3],4]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[[[0,9],2],3],4]")
        assertThat(result, `is`(expected))
    }

    @Test
    fun `tree explode test 2`() {
        val result = TreeNumberPair
            .fromString("[7,[6,[5,[4,[3,2]]]]]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[7,[6,[5,[7,0]]]]")
        assertThat(result, `is`(expected))
    }

    @Test
    fun `tree explode test 3`() {
        val result = TreeNumberPair
            .fromString("[[6,[5,[4,[3,2]]]],1]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[6,[5,[7,0]]],3]")
        assertThat(result, `is`(expected))
    }

    @Test
    fun `tree explode test 4`() {
        val result = TreeNumberPair
            .fromString("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")
        assertThat(result, `is`(expected))
    }

    @Test
    fun `tree explode test 5`() {
        val result = TreeNumberPair
            .fromString("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[3,[2,[8,0]]],[9,[5,[7,0]]]]")
        assertThat(result, `is`(expected))
    }

    @Test
    fun `complex addition`() {
        val input1 = "[[[[4,3],4],4],[7,[[8,4],9]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[1,1]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
        assertThat(result, `is`(expected))
    }

}