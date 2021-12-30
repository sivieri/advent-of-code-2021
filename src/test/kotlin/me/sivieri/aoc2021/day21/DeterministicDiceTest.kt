package me.sivieri.aoc2021.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class DeterministicDiceTest {

    @Test
    fun `test 200 extractions`() {
        val dice = DeterministicDice()
        val expected = (0 until 200).map { it % 100 + 1 }
        val result = expected.map { dice.next() }
        assertThat(result, `is`(expected))
    }

}