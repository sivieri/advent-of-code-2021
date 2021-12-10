package me.sivieri.aoc2021.day10

import java.lang.IllegalArgumentException

class SyntaxChecker {

    fun checkSyntax(line: String): Boolean = findFirstIllegalCharacter(line).second == null

    fun calculateIllegalLineScore(line: String): Int =
        illegalCharacterPoints[findFirstIllegalCharacter(line).second] ?: throw IllegalArgumentException("Line $line is not illegal")

    private fun findFirstIllegalCharacter(line: String): Pair<ArrayDeque<Char>, Char?> {
        val queue = ArrayDeque<Char>()
        val firstIllegalCharacter = line
            .toList()
            .fold(null as Char?) { acc, c ->
                acc
                    ?: if (c in openers) {
                        queue.addFirst(c)
                        null
                    } else { // c in closers
                        if (queue.first() == couplesFromCloser[c]) {
                            queue.removeFirst()
                            null
                        } else c
                    }
            }
        return Pair(queue, firstIllegalCharacter)
    }

    fun completeLine(line: String): String {
        val (queue, _) = findFirstIllegalCharacter(line)
        return queue
            .map { couplesFromOpener[it]!! }
            .joinToString("")
    }

    fun calculateCompleterScore(completerLine: String): Long = completerLine
        .toList()
        .fold(0L) { acc, c ->
            acc * 5 + incompleteCharacterPoints[c]!!
        }

    companion object {
        private val illegalCharacterPoints = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )
        private val incompleteCharacterPoints = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4
        )
        private val openers = listOf('(', '[', '{', '<')
        private val closers = listOf(')', ']', '}', '>')
        private val couplesFromCloser = closers.zip(openers).toMap()
        private val couplesFromOpener = openers.zip(closers).toMap()
    }

}