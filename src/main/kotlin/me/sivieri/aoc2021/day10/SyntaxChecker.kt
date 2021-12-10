package me.sivieri.aoc2021.day10

import java.lang.IllegalArgumentException

class SyntaxChecker {

    fun checkSyntax(line: String): Boolean = findFirstIllegalCharacter(line) == null

    fun calculateIllegalLineScore(line: String): Int =
        points[findFirstIllegalCharacter(line)] ?: throw IllegalArgumentException("Line $line is not illegal")

    private fun findFirstIllegalCharacter(line: String): Char? {
        val queue = ArrayDeque<Char>()
        return line
            .toList()
            .fold(null as Char?) { acc, c ->
                acc
                    ?: if (c in openers) {
                        queue.addFirst(c)
                        null
                    } else { // c in closers
                        if (queue.first() == couples[c]) {
                            queue.removeFirst()
                            null
                        } else c
                    }
            }
    }

    companion object {
        private val points = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )
        private val openers = listOf('(', '[', '{', '<')
        private val closers = listOf(')', ']', '}', '>')
        private val couples = closers.zip(openers).toMap()
    }

}