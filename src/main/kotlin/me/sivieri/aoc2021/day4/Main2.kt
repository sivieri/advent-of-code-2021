package me.sivieri.aoc2021.day4

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(4)
        val (numbers, boards) = data.split("\n", limit = 2)
        val bingo = Bingo.fromText(boards)
        val (winningNumber, winningBoard) = bingo.findLastWinner(numbers.split(",").map { it.toInt() })
        val finalScore = winningBoard!!.calculateScore() * winningNumber
        println(finalScore)
    }

}