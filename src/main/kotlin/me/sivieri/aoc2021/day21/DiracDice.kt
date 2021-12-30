package me.sivieri.aoc2021.day21

import java.lang.IllegalStateException

class DiracDice(
    private val player1: DicePlayer,
    private val player2: DicePlayer,
    val dice: Dice
) {
    fun play(player1Position: Int, player2Position: Int) {
        var p1 = player1Position
        var p2 = player2Position
        while (true) {
            val newP1 = roll(p1)
            p1 = newP1
            player1.score += newP1
            if (player1.score >= ENDGAME_SCORE) return
            val newP2 = roll(p2)
            p2 = newP2
            player2.score += newP2
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