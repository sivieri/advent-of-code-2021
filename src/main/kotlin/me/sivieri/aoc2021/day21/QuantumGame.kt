package me.sivieri.aoc2021.day21

import java.lang.Long.max

class QuantumGame(
    player1: DicePlayer,
    player2: DicePlayer,
) {
    private var games = mutableMapOf(
        QuantumGameStatus(player1, player2, 0) to 1L
    )
    private var player1games = 0L
    private var player2games = 0L

    fun play() {
        while (games.isNotEmpty()) {
            println("Cache: ${games.size}")
            println("Player 1 wins: $player1games")
            println("Player 2 wins: $player2games")
            val currentGames = games
                .flatMap { (game, repetitions) ->
                    diceValues
                        .entries
                        .flatMap { (n, times) ->
                            (0 until (times * repetitions)).map { _ ->
                                game.play(n)
                            }
                        }
                }
            currentGames.forEach { game ->
                if (games.containsKey(game)) games[game] = games[game]!! + 1L
                else games[game] = 1L
            }
            val wonGames = games
                .filter { (game, _) ->
                    if (game.isWon()) {
                        if (game.player1.score >= QuantumGameStatus.ENDGAME_SCORE) player1games++
                        else player2games++
                    }
                    game.isWon()
                }
                .keys
            wonGames.forEach { games.remove(it) }
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
