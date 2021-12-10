package me.sivieri.aoc2021.day10

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class SyntaxCheckerTest {

    @Test
    fun `valid chunk 1`() {
        val line = "[<>({}){}[([])<>]]"
        val checker = SyntaxChecker()
        assertThat(checker.checkSyntax(line), `is`(true))
    }

    @Test
    fun `valid chunk 2`() {
        val line = "(((((((((())))))))))"
        val checker = SyntaxChecker()
        assertThat(checker.checkSyntax(line), `is`(true))
    }

    @Test
    fun `invalid chunk 1`() {
        val line = "{()()()>"
        val checker = SyntaxChecker()
        assertThat(checker.checkSyntax(line), `is`(false))
    }

    @Test
    fun `invalid chunk 2`() {
        val line = "<([]){()}[{}])"
        val checker = SyntaxChecker()
        assertThat(checker.checkSyntax(line), `is`(false))
    }

    @Test
    fun `part 1 example`() {
        val input = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent().split("\n")
        val checker = SyntaxChecker()
        val result = input
            .filterNot { checker.checkSyntax(it) }
            .sumOf { checker.calculateIllegalLineScore(it) }
        assertThat(result, `is`(26397))
    }

}