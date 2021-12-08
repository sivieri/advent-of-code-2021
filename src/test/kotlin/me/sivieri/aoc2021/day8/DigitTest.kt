package me.sivieri.aoc2021.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.lang.IllegalStateException

class DigitTest {

    @Test
    fun `digit 0`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to true,
                'c' to true,
                'd' to true,
                'e' to true,
                'f' to true,
                'g' to false,
            )
        )
        assertThat(digit.digit(), `is`(0))
    }

    @Test
    fun `digit 1`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to false,
                'b' to true,
                'c' to true,
                'd' to false,
                'e' to false,
                'f' to false,
                'g' to false,
            )
        )
        assertThat(digit.digit(), `is`(1))
    }

    @Test
    fun `digit 2`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to true,
                'c' to false,
                'd' to true,
                'e' to true,
                'f' to false,
                'g' to true,
            )
        )
        assertThat(digit.digit(), `is`(2))
    }

    @Test
    fun `digit 3`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to true,
                'c' to true,
                'd' to true,
                'e' to false,
                'f' to false,
                'g' to true,
            )
        )
        assertThat(digit.digit(), `is`(3))
    }

    @Test
    fun `digit 4`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to false,
                'b' to true,
                'c' to true,
                'd' to false,
                'e' to false,
                'f' to true,
                'g' to true,
            )
        )
        assertThat(digit.digit(), `is`(4))
    }

    @Test
    fun `digit 5`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to false,
                'c' to true,
                'd' to true,
                'e' to false,
                'f' to true,
                'g' to true,
            )
        )
        assertThat(digit.digit(), `is`(5))
    }

    @Test
    fun `digit 6`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to false,
                'c' to true,
                'd' to true,
                'e' to true,
                'f' to true,
                'g' to true,
            )
        )
        assertThat(digit.digit(), `is`(6))
    }

    @Test
    fun `digit 7`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to true,
                'c' to true,
                'd' to false,
                'e' to false,
                'f' to false,
                'g' to false,
            )
        )
        assertThat(digit.digit(), `is`(7))
    }

    @Test
    fun `digit 8`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to true,
                'c' to true,
                'd' to true,
                'e' to true,
                'f' to true,
                'g' to true,
            )
        )
        assertThat(digit.digit(), `is`(8))
    }

    @Test
    fun `digit 9`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to true,
                'c' to true,
                'd' to true,
                'e' to false,
                'f' to true,
                'g' to true,
            )
        )
        assertThat(digit.digit(), `is`(9))
    }

    @Test(expected = IllegalStateException::class)
    fun `wrong digit`() {
        val digit = Digit(
            mapOf(
                0 to 'a',
                1 to 'b',
                2 to 'c',
                3 to 'd',
                4 to 'e',
                5 to 'f',
                6 to 'g'
            ),
            mapOf(
                'a' to true,
                'b' to false,
                'c' to true,
                'd' to false,
                'e' to true,
                'f' to false,
                'g' to true,
            )
        )
        digit.digit()
    }

}