package me.sivieri.aoc2021.day16

class BitsParser {

    fun parseMessage(input: String): Packet {
        val msg = input
            .toList()
            .joinToString("") { hexMapping[it]!! }
        return parse(msg)
    }

    private fun parse(msg: String): Packet {
        val parserStatus = ArrayDeque<BitsParserStatus>()
        val packets = ArrayDeque<Packet>()
        val operatorVersions = ArrayDeque<Pair<Int, Int>>()
        var currentMsg = msg
        while (currentMsg.isNotEmpty()) {
            if (parserStatus.first() == BitsParserStatus.END_OPERATOR) {
                parserStatus.removeFirst()
                val subPackets = mutableListOf<Packet>()
                while (parserStatus.removeFirst() != BitsParserStatus.BEGIN_OPERATOR) {
                    subPackets.add(packets.removeFirst())
                }
                val (version, idType) = operatorVersions.removeFirst()
                val operator = Operator(version, idType, subPackets.toList())
                packets.addFirst(operator)
                parserStatus.addFirst(BitsParserStatus.OPERATOR)
            }
            else {
                val version = Integer.parseInt(currentMsg.substring(0, 3), 2)
                val typeId = Integer.parseInt(currentMsg.substring(3, 6), 2)
                if (typeId == LiteralValue.LITERAL_VALUE_TYPE_ID) {
                    val (index, literalValue) = parseLiteralValue(version, currentMsg.substring(6))
                    packets.addFirst(literalValue)
                    parserStatus.addFirst(BitsParserStatus.LITERAL_VALUE)
                    currentMsg = currentMsg.substring(index + 6)
                }
                else {
                    parserStatus.addFirst(BitsParserStatus.BEGIN_OPERATOR)
                    val mode = currentMsg[6].toString().toInt()
                    if (mode == 0) {
                        val subPacketsLength = Integer.parseInt(currentMsg.substring(7, 7 + OPERATOR_MODE_ZERO_LENGTH), 2)
                        currentMsg = currentMsg.substring(7 + OPERATOR_MODE_ZERO_LENGTH)
                    }
                    else {
                        val subPacketsNumber = Integer.parseInt(currentMsg.substring(7, 7 + OPERATOR_MODE_ONE_LENGTH), 2)
                        currentMsg = currentMsg.substring(7 + OPERATOR_MODE_ONE_LENGTH)
                    }
                }
            }
        }
        return packets.first()
    }

    private fun parseLiteralValue(version: Int, msg: String): Pair<Int, LiteralValue> {
        TODO("Not yet implemented")
    }

    companion object {
        private val hexMapping = mapOf(
            '0' to "0000",
            '1' to "0001",
            '2' to "0010",
            '3' to "0011",
            '4' to "0100",
            '5' to "0101",
            '6' to "0110",
            '7' to "0111",
            '8' to "1000",
            '9' to "1001",
            'A' to "1010",
            'B' to "1011",
            'C' to "1100",
            'D' to "1101",
            'E' to "1110",
            'F' to "1111"
        )
        private const val OPERATOR_MODE_ZERO_LENGTH = 15
        private const val OPERATOR_MODE_ONE_LENGTH = 11
    }

}