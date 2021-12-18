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
        var i = 0
        while (
            (
                i < msg.length
                && (msg.length - i) >= MIN_PACKET_LENGTH
            )
            || checkEndOperatorExistence(parserStatus)
        ) {
            if (parserStatus.firstOrNull()?.label == BitsParserStatusLabel.END_OPERATOR) {
                parserStatus.removeFirst()
                val subPackets = mutableListOf<Packet>()
                var status = parserStatus.removeFirst()
                while (status.label != BitsParserStatusLabel.BEGIN_OPERATOR) {
                    subPackets.add(packets.removeFirst())
                    status = parserStatus.removeFirst()
                }
                status as BeginOperatorStatus
                val operator = Operator(status.version, status.typeId, subPackets.toList().reversed())
                packets.addFirst(operator)
                parserStatus.addFirst(OperatorStatus(i - status.startIndex))
                if (checkBeginOperatorExistence(parserStatus) && checkOperatorEnding(parserStatus))
                    parserStatus.addFirst(EndOperatorStatus())
            }
            else {
                val version = Integer.parseInt(msg.substring(i, i + 3), 2)
                val typeId = Integer.parseInt(msg.substring(i + 3, i + 6), 2)
                if (typeId == LiteralValue.LITERAL_VALUE_TYPE_ID) {
                    val (index, literalValue) = parseLiteralValue(version, msg.substring(i + 6))
                    packets.addFirst(literalValue)
                    parserStatus.addFirst(LiteralValueStatus(6 + index))
                    if (checkBeginOperatorExistence(parserStatus) && checkOperatorEnding(parserStatus))
                        parserStatus.addFirst(EndOperatorStatus())
                    i += 6 + index
                }
                else {
                    val mode = msg[i + 6].toString().toInt()
                    i += if (mode == 0) {
                        val subPacketsLength = Integer.parseInt(msg.substring(i + 7, i + 7 + OPERATOR_MODE_ZERO_LENGTH), 2)
                        parserStatus.addFirst(BeginOperatorStatus(i + 7 + OPERATOR_MODE_ZERO_LENGTH, version, typeId, subPacketsLength, 0))
                        7 + OPERATOR_MODE_ZERO_LENGTH
                    }
                    else {
                        val subPacketsNumber = Integer.parseInt(msg.substring(i + 7, i + 7 + OPERATOR_MODE_ONE_LENGTH), 2)
                        parserStatus.addFirst(BeginOperatorStatus(i + 7 + OPERATOR_MODE_ONE_LENGTH, version, typeId, 0, subPacketsNumber))
                        7 + OPERATOR_MODE_ONE_LENGTH
                    }
                }
            }
        }
        return packets.first()
    }

    private fun checkEndOperatorExistence(stack: ArrayDeque<BitsParserStatus>): Boolean =
        stack.find { it is EndOperatorStatus } != null

    private fun checkBeginOperatorExistence(stack: ArrayDeque<BitsParserStatus>): Boolean =
        stack.find { it is BeginOperatorStatus } != null

    private fun checkOperatorEnding(stack: ArrayDeque<BitsParserStatus>): Boolean {
        var subPacketLength = 0
        var subPacketNumber = 0
        for (element in stack) {
            when (element) {
                is LiteralValueStatus -> {
                    subPacketLength += element.subPacketLength
                    subPacketNumber++
                }
                is OperatorStatus -> {
                    subPacketLength += element.subPacketLength
                    subPacketNumber++
                }
                is BeginOperatorStatus -> {
                    if (element.subPacketLength == 0) return element.subPacketNumber - subPacketNumber == 0
                    if (element.subPacketNumber == 0) return element.subPacketLength - subPacketLength == 0
                }
                else -> { /* this should not happen */ }
            }
        }
        return true
    }

    private fun parseLiteralValue(version: Int, msg: String): Pair<Int, LiteralValue> {
        val result = StringBuffer()
        var index = 0
        var current = ""
        do {
            current = msg.substring(index, index + 5)
            index += 5
            result.append(current.substring(1, 5))
        } while (current[0] != '0')
        return Pair(index, LiteralValue(version, Integer.parseInt(result.toString(), 2)))
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
        private const val MIN_PACKET_LENGTH = 11 // a literal value
    }

}