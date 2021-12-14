package me.sivieri.aoc2021.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class PolymerizationTest {

    private val input = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent()

    @Test
    fun `part 1 step 1`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertions(1)
        assertThat(result, `is`("NCNBCHB"))
    }

    @Test
    fun `part 1 step 2`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertions(2)
        assertThat(result, `is`("NBCCNBBBCBHCB"))
    }

    @Test
    fun `part 1 step 3`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertions(3)
        assertThat(result, `is`("NBBBCNCCNBBNBNBBCHBHHBCHB"))
    }

    @Test
    fun `part 1 step 4`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertions(4)
        assertThat(result, `is`("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"))
    }

    @Test
    fun `part 1 step 10`() {
        val polymerization = Polymerization(input)
        val polymer = polymerization.applyInsertions(10)
        val result = Polymerization.calculateMostMinusLeastCommon(polymer)
        assertThat(result, `is`(1588))
    }

    @Test
    fun `part 2 step 1`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertionsLong(1)
        assertThat(result.values.sum().toInt(), `is`("NCNBCHB".windowed(size = 2).size + 1))
    }

    @Test
    fun `part 2 step 2`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertionsLong(2)
        assertThat(result.values.sum().toInt(), `is`("NBCCNBBBCBHCB".windowed(size = 2).size + 1))
    }

    @Test
    fun `part 2 step 3`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertionsLong(3)
        assertThat(result.values.sum().toInt(), `is`("NBBBCNCCNBBNBNBBCHBHHBCHB".windowed(size = 2).size + 1))
    }

    @Test
    fun `part 2 step 4`() {
        val polymerization = Polymerization(input)
        val result = polymerization.applyInsertionsLong(4)
        assertThat(result.values.sum().toInt(), `is`("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB".windowed(size = 2).size + 1))
    }

    @Test
    fun `part 2 step 10`() {
        val polymerization = Polymerization(input)
        val polymer = polymerization.applyInsertionsLong(10)
        val result = Polymerization.calculateMostMinusLeastCommon(polymer)
        assertThat(result, `is`(1588))
    }

}