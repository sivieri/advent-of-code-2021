package me.sivieri.aoc2021.day10

import me.sivieri.aoc2021.Utils
import me.sivieri.aoc2021.getMiddleElement

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(10)
        val checker = SyntaxChecker()
        val scores = data
            .filter { checker.checkSyntax(it) }
            .map {
                val completer = checker.completeLine(it)
                Pair(completer, checker.calculateCompleterScore(completer))
            }
            .sortedBy { it.second }
        val result = scores.getMiddleElement()
        println(result)
    }

}