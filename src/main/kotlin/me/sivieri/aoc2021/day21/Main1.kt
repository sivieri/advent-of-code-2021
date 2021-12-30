package me.sivieri.aoc2021.day21

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val player1 = DicePlayer(1)
        val player2 = DicePlayer(2)
        val game = DiracDice(player1, player2, DeterministicDice())
        game.play(7, 9)
        val loser = game.getLoser()
        println(loser.score * game.dice.rolls)
    }

}