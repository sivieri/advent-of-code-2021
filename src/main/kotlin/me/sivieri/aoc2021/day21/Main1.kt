package me.sivieri.aoc2021.day21

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val player1 = DicePlayer(1, 7)
        val player2 = DicePlayer(2, 9)
        val game = DiracDice(player1, player2, DeterministicDice())
        game.play()
        val loser = game.getLoser()
        println(loser.score * game.dice.rolls)
    }

}