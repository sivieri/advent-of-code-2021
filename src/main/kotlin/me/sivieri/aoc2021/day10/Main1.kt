package me.sivieri.aoc2021.day10

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(10)
        val checker = SyntaxChecker()
        val result = data
            .filterNot { checker.checkSyntax(it) }
            .sumOf { checker.calculateIllegalLineScore(it) }
        println(result)
    }

}