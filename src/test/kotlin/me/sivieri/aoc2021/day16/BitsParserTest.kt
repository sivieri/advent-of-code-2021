package me.sivieri.aoc2021.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BitsParserTest {

    private val bitsParser = BitsParser()

    @Test
    fun `literal value`() {
        val result = bitsParser.parseMessage("D2FE28")
        val expected = LiteralValue(6, 2021)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `operator 1`() {
        val result = bitsParser.parseMessage("38006F45291200")
        val expected = Operator(
            1,
            6,
            listOf(
                LiteralValue(6, 10),
                LiteralValue(2, 20)
            )
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `operator 2`() {
        val result = bitsParser.parseMessage("EE00D40C823060")
        val expected = Operator(
            7,
            3,
            listOf(
                LiteralValue(2, 1),
                LiteralValue(4, 2),
                LiteralValue(1, 3)
            )
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `part 1 example 1`() {
        val result = bitsParser.parseMessage("8A004A801A8002F478")
        val expected = Operator(
            4,
            2,
            listOf(
                Operator(
                    1,
                    2,
                    listOf(
                        Operator(
                            5,
                            2,
                            listOf(
                                LiteralValue(6, 15)
                            )
                        )
                    )
                )
            )
        )
        assertThat(result, `is`(expected))
        assertThat(result.versionSum(), `is`(16))
    }

    @Test
    fun `part 1 example 2`() {
        val result = bitsParser.parseMessage("620080001611562C8802118E34")
        val expected = Operator(
            3,
            0,
            listOf(
                Operator(
                    0,
                    0,
                    listOf(
                        LiteralValue(0, 10),
                        LiteralValue(5, 11)
                    )
                ),
                Operator(
                    1,
                    0,
                    listOf(
                        LiteralValue(0, 12),
                        LiteralValue(3, 13)
                    )
                )
            )
        )
        assertThat(result, `is`(expected))
        assertThat(result.versionSum(), `is`(12))
    }

    @Test
    fun `part 1 example 3`() {
        val result = bitsParser.parseMessage("C0015000016115A2E0802F182340")
        val expected = Operator(
            6,
            0,
            listOf(
                Operator(
                    0,
                    0,
                    listOf(
                        LiteralValue(0, 10),
                        LiteralValue(6, 11)
                    )
                ),
                Operator(
                    4,
                    0,
                    listOf(
                        LiteralValue(7, 12),
                        LiteralValue(0, 13)
                    )
                )
            )
        )
        assertThat(result, `is`(expected))
        assertThat(result.versionSum(), `is`(23))
    }

    @Test
    fun `part 1 example 4`() {
        val result = bitsParser.parseMessage("A0016C880162017C3686B18A3D4780")
        val expected = Operator(
            5,
            0,
            listOf(
                Operator(
                    1,
                    0,
                    listOf(
                        Operator(
                            3,
                            0,
                            listOf(
                                LiteralValue(7, 6),
                                LiteralValue(6, 6),
                                LiteralValue(5, 12),
                                LiteralValue(2, 15),
                                LiteralValue(2, 15)
                            )
                        )
                    )
                )
            )
        )
        assertThat(result, `is`(expected))
        assertThat(result.versionSum(), `is`(31))
    }

}