package me.sivieri.aoc2021.day10

import me.sivieri.aoc2021.getMiddleElement
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

    @Test
    fun `completer 1`() {
        val line = "[({(<(())[]>[[{[]{<()<>>"
        val checker = SyntaxChecker()
        val completer = checker.completeLine(line)
        assertThat(completer, `is`("}}]])})]"))
    }

    @Test
    fun `completer 2`() {
        val line = "[(()[<>])]({[<{<<[]>>("
        val checker = SyntaxChecker()
        val completer = checker.completeLine(line)
        assertThat(completer, `is`(")}>]})"))
    }

    @Test
    fun `completer 3 score`() {
        val line = "<{([{{}}[<[[[<>{}]]]>[]]"
        val checker = SyntaxChecker()
        val completer = checker.completeLine(line)
        val score = checker.calculateCompleterScore(completer)
        assertThat(completer, `is`("])}>"))
        assertThat(score, `is`(294))
    }

    @Test
    fun `part 2 example`() {
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
        val scores = input
            .filter { checker.checkSyntax(it) }
            .map { checker.calculateCompleterScore(checker.completeLine(it)) }
            .sorted()
        val result = scores.getMiddleElement()
        assertThat(result, `is`(288957))
    }

}