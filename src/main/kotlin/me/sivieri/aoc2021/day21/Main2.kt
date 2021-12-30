package me.sivieri.aoc2021.day21

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val player1 = DicePlayer(1, 7)
        val player2 = DicePlayer(2, 9)
        val game = QuantumGame(player1, player2)
        game.play()
        println(game.maxGames())
    }

}