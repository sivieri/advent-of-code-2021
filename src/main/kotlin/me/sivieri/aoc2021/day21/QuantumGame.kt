package me.sivieri.aoc2021.day21

import java.lang.Long.max

class QuantumGame(
    player1: DicePlayer,
    player2: DicePlayer,
) {
    private var games = listOf(
        QuantumGameStatus(player1, player2, 0)
    )
    private var player1games = 0L
    private var player2games = 0L

    fun play() {
        while (games.isNotEmpty()) {
            games = games
                .flatMap { game ->
                    diceValues
                        .entries
                        .flatMap { (n, times) ->
                            (0 until times).map { _ ->
                                game.play(n)
                            }
                        }
                }
                .mapNotNull { game ->
                    if (game.isWon()) {
                        if (game.player1.score >= QuantumGameStatus.ENDGAME_SCORE) player1games++
                        else player2games++
                        null
                    }
                    else game
                }
        }
    }

    fun maxGames(): Long = max(player1games, player2games)

    companion object {
        private val diceValues = mapOf(
            3 to 1,
            4 to 3,
            5 to 6,
            6 to 7,
            7 to 6,
            8 to 3,
            9 to 1
        )
    }

}
