package me.sivieri.aoc2021.day16

enum class BitsParserStatusLabel {

    LITERAL_VALUE,
    OPERATOR,
    BEGIN_OPERATOR,
    END_OPERATOR;

}

sealed class BitsParserStatus(
    val label: BitsParserStatusLabel
)

class LiteralValueStatus(
    val subPacketLength: Int,
    val literalValue: LiteralValue
): BitsParserStatus(BitsParserStatusLabel.LITERAL_VALUE)

class OperatorStatus(
    val subPacketLength: Int,
    val operator: Operator
): BitsParserStatus(BitsParserStatusLabel.OPERATOR)

class BeginOperatorStatus(
    val startIndex: Int,
    val version: Int,
    val typeId: Int,
    val subPacketLength: Int,
    val subPacketNumber: Int
): BitsParserStatus(BitsParserStatusLabel.BEGIN_OPERATOR)

class EndOperatorStatus: BitsParserStatus(BitsParserStatusLabel.END_OPERATOR)
