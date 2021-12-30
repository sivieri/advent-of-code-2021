package me.sivieri.aoc2021.day21

import java.lang.IllegalStateException

class DiracDice(
    private val player1: DicePlayer,
    private val player2: DicePlayer,
    val dice: Dice
) {
    fun play() {
        while (true) {
            player1.position = roll(player1.position)
            player1.score += player1.position
            if (player1.score >= ENDGAME_SCORE) return
            player2.position = roll(player2.position)
            player2.score += player2.position
            if (player2.score >= ENDGAME_SCORE) return
        }
    }

    private fun roll(startingPosition: Int): Int {
        val n = (dice.next() + dice.next() + dice.next()) % BOARD_SIZE
        return if (startingPosition + n > BOARD_SIZE) startingPosition + n - BOARD_SIZE
        else startingPosition + n
    }

    fun getLoser(): DicePlayer =
        if (player1.score >= ENDGAME_SCORE) player2
        else if (player2.score >= ENDGAME_SCORE) player1
        else throw IllegalStateException("Game has not been won yet")

    companion object {
        private const val BOARD_SIZE = 10
        private const val ENDGAME_SCORE = 1000
    }

}