package me.sivieri.aoc2021.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.lang.Long.max

class QuantumGameTest {

    @Test
    fun `part 2 example`() {
        val player1 = QuantumDicePlayer(4)
        val player2 = QuantumDicePlayer(8)
        val game = QuantumGame(player1, player2)
        game.play()
        assertThat(game.maxGames(), `is`(444356092776315))
    }

    @Test
    fun `part 2 java example`() {
        val game = QuantumGameJava(4, 8)
        game.play()
        val pl1 = game.wonGamesForPlayer1()
        val pl2 = game.wonGamesForPlayer2()
        val result = max(pl1, pl2)
        assertThat(result, `is`(444356092776315))
    }

}