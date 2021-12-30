package me.sivieri.aoc2021.day21

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val player1 = QuantumDicePlayer(7)
        val player2 = QuantumDicePlayer(9)
        val game = QuantumGame(player1, player2)
        game.play()
        println(game.maxGames())
    }

}