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
        assertThat(tree.toString(), `is`(expected.toString()))
    }

    @Test
    fun `parsing test 2`() {
        val input = "[[1,2],3]"
        val tree = TreeNumberPair.fromString(input)
        val expected = TreePair(TreePair(TreeNumber(1), TreeNumber(2)), TreeNumber(3))
        assertThat(tree.toString(), `is`(expected.toString()))
    }

    @Test
    fun `parsing test 3`() {
        val input = "[9,[8,7]]"
        val tree = TreeNumberPair.fromString(input)
        val expected = TreePair(TreeNumber(9), TreePair(TreeNumber(8), TreeNumber(7)))
        assertThat(tree.toString(), `is`(expected.toString()))
    }

    @Test
    fun `parsing test 4`() {
        val input = "[[1,9],[8,5]]"
        val tree = TreeNumberPair.fromString(input)
        val expected = TreePair(TreePair(TreeNumber(1), TreeNumber(9)), TreePair(TreeNumber(8), TreeNumber(5)))
        assertThat(tree.toString(), `is`(expected.toString()))
    }

    @Test
    fun `simple addition`() {
        val input1 = "[1,2]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[[3,4],5]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[1,2],[[3,4],5]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `number split test 1`() {
        val input = TreeNumber(10)
        val result = input.split()
        assertThat(result.toString(), `is`(TreePair(TreeNumber(5), TreeNumber(5)).toString()))
    }

    @Test
    fun `number split test 2`() {
        val input = TreeNumber(11)
        val result = input.split()
        assertThat(result.toString(), `is`(TreePair(TreeNumber(5), TreeNumber(6)).toString()))
    }

    @Test
    fun `number split test 3`() {
        val input = TreeNumber(12)
        val result = input.split()
        assertThat(result.toString(), `is`(TreePair(TreeNumber(6), TreeNumber(6)).toString()))
    }

    @Test
    fun `tree explode test 1`() {
        val result = TreeNumberPair
            .fromString("[[[[[9,8],1],2],3],4]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[[[0,9],2],3],4]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `tree explode test 2`() {
        val result = TreeNumberPair
            .fromString("[7,[6,[5,[4,[3,2]]]]]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[7,[6,[5,[7,0]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `tree explode test 3`() {
        val result = TreeNumberPair
            .fromString("[[6,[5,[4,[3,2]]]],1]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[6,[5,[7,0]]],3]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `tree explode test 4`() {
        val result = TreeNumberPair
            .fromString("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[3,[2,[8,0]]],[9,[5,[7,0]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `tree explode test 5`() {
        val result = TreeNumberPair
            .fromString("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")
            .reduce()
        val expected = TreeNumberPair
            .fromString("[[3,[2,[8,0]]],[9,[5,[7,0]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `complex addition`() {
        val input1 = "[[[[4,3],4],4],[7,[[8,4],9]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[1,1]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 1`() {
        val input1 = "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 2`() {
        val input1 = "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 3`() {
        val input1 = "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 4`() {
        val input1 = "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[7,[5,[[3,8],[1,4]]]]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 5`() {
        val input1 = "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[[2,[2,2]],[8,[8,1]]]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 6`() {
        val input1 = "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[2,9]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 7`() {
        val input1 = "[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[1,[[[9,3],9],[[9,0],[0,7]]]]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }
    @Test
    fun `more complex addition 8`() {
        val input1 = "[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[[[5,[7,4]],7],1]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `more complex addition 9`() {
        val input1 = "[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]"
        val tree1 = TreeNumberPair.fromString(input1)
        val input2 = "[[[[4,2],2],6],[8,7]]"
        val tree2 = TreeNumberPair.fromString(input2)
        val result = tree1 + tree2
        val expected = TreeNumberPair.fromString("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")
        assertThat(result.toString(), `is`(expected.toString()))
    }

    @Test
    fun `magnitude test 1`() {
        val result = TreeNumberPair.fromString("[9,1]").magnitude()
        assertThat(result, `is`(29L))
    }

    @Test
    fun `magnitude test 2`() {
        val result = TreeNumberPair.fromString("[1,9]").magnitude()
        assertThat(result, `is`(21L))
    }

    @Test
    fun `magnitude test 3`() {
        val result = TreeNumberPair.fromString("[[9,1],[1,9]]").magnitude()
        assertThat(result, `is`(129L))
    }

    @Test
    fun `magnitude test 4`() {
        val result = TreeNumberPair.fromString("[[1,2],[[3,4],5]]").magnitude()
        assertThat(result, `is`(143L))
    }

    @Test
    fun `magnitude test 5`() {
        val result = TreeNumberPair.fromString("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]").magnitude()
        assertThat(result, `is`(1384L))
    }

    @Test
    fun `magnitude test 6`() {
        val result = TreeNumberPair.fromString("[[[[1,1],[2,2]],[3,3]],[4,4]]").magnitude()
        assertThat(result, `is`(445L))
    }
    @Test
    fun `magnitude test 7`() {
        val result = TreeNumberPair.fromString("[[[[3,0],[5,3]],[4,4]],[5,5]]").magnitude()
        assertThat(result, `is`(791L))
    }

    @Test
    fun `magnitude test 81`() {
        val result = TreeNumberPair.fromString("[[[[5,0],[7,4]],[5,5]],[6,6]]").magnitude()
        assertThat(result, `is`(1137L))
    }

    @Test
    fun `magnitude test 9`() {
        val result = TreeNumberPair.fromString("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").magnitude()
        assertThat(result, `is`(3488L))
    }


}