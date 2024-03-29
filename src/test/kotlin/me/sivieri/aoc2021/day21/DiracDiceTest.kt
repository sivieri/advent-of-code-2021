package me.sivieri.aoc2021.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class DiracDiceTest {

    @Test
    fun `part 1 test`() {
        val player1 = DicePlayer(1, 4)
        val player2 = DicePlayer(2, 8)
        val game = DiracDice(player1, player2, DeterministicDice())
        game.play()
        val loser = game.getLoser()
        assertThat(loser.score * game.dice.rolls, `is`(739785))
    }

}