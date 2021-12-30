package me.sivieri.aoc2021.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class QuantumGameTest {

    @Test
    fun `part 2 example`() {
        val player1 = QuantumDicePlayer(4)
        val player2 = QuantumDicePlayer(8)
        val game = QuantumGame(player1, player2)
        game.play()
        assertThat(game.maxGames(), `is`(444356092776315))
    }

}