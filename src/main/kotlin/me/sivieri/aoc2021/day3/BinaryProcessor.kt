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

    fun calculateLifeSupportRating(
        data: List<String>
    ): LifeSupportRating {
        val binary = data.map { it.toCharArray().toTypedArray() }
        val oxygen = extractSingleNumberByDigitFrequency(binary) { if (it['0']!! > it['1']!!) '0' else '1' }
        val co2 = extractSingleNumberByDigitFrequency(binary) { if (it['1']!! < it['0']!!) '1' else '0' }
        return LifeSupportRating(oxygen, co2)
    }

    private fun extractSingleNumberByDigitFrequency(
        binary: List<Array<Char>>,
        frequencyCondition: (Map<Char, Int>) -> Char
    ) =
        (0 until binary.first().size)
            .fold(binary) { numbers, position ->
                if (numbers.size == 1) numbers
                else {
                    val cnt = numbers
                        .map { it[position] }
                        .groupingBy { it }
                        .eachCount()
                    val condition = frequencyCondition(cnt)
                    numbers.filter { it[position] == condition }
                }
            }
            .first()
            .joinToString("")
            .toInt(radix = 2)

}