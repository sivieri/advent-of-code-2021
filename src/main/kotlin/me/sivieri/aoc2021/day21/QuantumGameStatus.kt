package me.sivieri.aoc2021.day21

data class QuantumGameStatus(
    val player1: QuantumDicePlayer,
    val player2: QuantumDicePlayer,
    val round: Int
) {

    fun play(n: Int): QuantumGameStatus =
        if (round % 2 == 0) {
            val value = if (player1.position + n > BOARD_SIZE) player1.position + n - BOARD_SIZE
            else player1.position + n
            QuantumGameStatus(
                player1.copy(position = value, score = player1.score + value),
                player2.copy(),
                round + 1
            )
        }
        else {
            val value = if (player2.position + n > BOARD_SIZE) player2.position + n - BOARD_SIZE
            else player2.position + n
            QuantumGameStatus(
                player1.copy(),
                player2.copy(position = value, score = player2.score + value),
                round + 1
            )
        }

    fun isWon(): Boolean = player1.score >= ENDGAME_SCORE || player2.score >= ENDGAME_SCORE

    companion object {
        private const val BOARD_SIZE = 10
        const val ENDGAME_SCORE = 21
    }

}
