package me.sivieri.aoc2021.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TreeNumberPairAssignmentTest {

    @Test
    fun `part 1 example 1`() {
        val input = """
            [1,1]
            [2,2]
            [3,3]
            [4,4]
        """.trimIndent().split("\n")
        val expected = "[[[[1,1],[2,2]],[3,3]],[4,4]]"
        val result = TreeNumberPairAssignment.sum(input)
        assertThat(result.toString(), `is`(expected))
    }

    @Test
    fun `part 1 example 2`() {
        val input = """
            [1,1]
            [2,2]
            [3,3]
            [4,4]
            [5,5]
        """.trimIndent().split("\n")
        val expected = "[[[[3,0],[5,3]],[4,4]],[5,5]]"
        val result = TreeNumberPairAssignment.sum(input)
        assertThat(result.toString(), `is`(expected))
    }

    @Test
    fun `part 1 example 3`() {
        val input = """
            [1,1]
            [2,2]
            [3,3]
            [4,4]
            [5,5]
            [6,6]
        """.trimIndent().split("\n")
        val expected = "[[[[5,0],[7,4]],[5,5]],[6,6]]"
        val result = TreeNumberPairAssignment.sum(input)
        assertThat(result.toString(), `is`(expected))
    }

    @Test
    fun `part 1 example 4`() {
        val input = """
            [[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
            [7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
            [[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]
            [[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]
            [7,[5,[[3,8],[1,4]]]]
            [[2,[2,2]],[8,[8,1]]]
            [2,9]
            [1,[[[9,3],9],[[9,0],[0,7]]]]
            [[[5,[7,4]],7],1]
            [[[[4,2],2],6],[8,7]]
        """.trimIndent().split("\n")
        val expected = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
        val result = TreeNumberPairAssignment.sum(input)
        assertThat(result.toString(), `is`(expected))
    }

    @Test
    fun `part 1 example 5`() {
        val input = """
            [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
            [[[5,[2,8]],4],[5,[[9,9],0]]]
            [6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
            [[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
            [[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
            [[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
            [[[[5,4],[7,7]],8],[[8,3],8]]
            [[9,3],[[9,9],[6,[4,9]]]]
            [[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
            [[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
        """.trimIndent().split("\n")
        val expected = "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]"
        val result = TreeNumberPairAssignment.sum(input)
        assertThat(result.toString(), `is`(expected))
        assertThat(result.magnitude(), `is`(4140L))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
            [[[5,[2,8]],4],[5,[[9,9],0]]]
            [6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
            [[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
            [[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
            [[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
            [[[[5,4],[7,7]],8],[[8,3],8]]
            [[9,3],[[9,9],[6,[4,9]]]]
            [[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
            [[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
        """.trimIndent().split("\n")
        val result = TreeNumberPairAssignment.maxMagnitude(input)
        assertThat(result, `is`(3993L))
    }

}