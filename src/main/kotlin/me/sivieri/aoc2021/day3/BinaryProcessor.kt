package me.sivieri.aoc2021.day3

class BinaryProcessor {

    fun calculatePowerConsumption(
        data: List<String>
    ): PowerConsumption {
        val binary = data.map { it.toCharArray().toTypedArray() }
        val pairs = (0 until binary.first().size).fold(emptyList<Pair<Char, Char>>()) { acc, number ->
            val pair = binary
                .map { it[number] }
                .partition { it == '0' }
                .let { Pair(it.first.size, it.second.size) }
            if (pair.first > pair.second) acc.plus(Pair('0', '1'))
            else acc.plus(Pair('1', '0'))
        }
        val gamma = pairs
            .map { it.first }
            .joinToString("")
            .toInt(radix = 2)
        val epsilon = pairs
            .map { it.second }
            .joinToString("")
            .toInt(radix = 2)
        return PowerConsumption(gamma, epsilon)
    }

}