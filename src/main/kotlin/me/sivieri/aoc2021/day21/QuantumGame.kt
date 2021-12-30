package me.sivieri.aoc2021.day21

import java.lang.Long.max

class QuantumGame(
    player1: DicePlayer,
    player2: DicePlayer,
) {
    private var games = mutableMapOf(
        QuantumGameStatus(player1, player2, 0) to 1L
    )

    fun play() {
        while (games.keys.any { !it.isWon() }) {
            println("Cache: ${games.size}")
            val currentGames = mutableMapOf<QuantumGameStatus, Long>()
            games.forEach { (game, count) ->
                playGame(game, count, currentGames)
            }
            games = currentGames
        }
    }

    private fun playGame(
        game: QuantumGameStatus,
        count: Long,
        currentGames: MutableMap<QuantumGameStatus, Long>
    ) {
        if (game.isWon()) {
            if (currentGames.containsKey(game)) currentGames[game] = currentGames[game]!! + count
            else currentGames[game] = count
        }
        diceValues.forEach { (n, times) ->
            val newGame = game.play(n)
            val newCount = count * times
            if (currentGames.containsKey(newGame)) currentGames[newGame] = currentGames[newGame]!! + newCount
            else currentGames[newGame] = newCount
        }
    }

    fun maxGames(): Long {
        val player1games = games
            .entries
            .filter { it.key.player1.score >= QuantumGameStatus.ENDGAME_SCORE }
            .sumOf { it.value }
        val player2games = games
            .entries
            .filter { it.key.player2.score >= QuantumGameStatus.ENDGAME_SCORE }
            .sumOf { it.value }
        return max(player1games, player2games)
    }

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
