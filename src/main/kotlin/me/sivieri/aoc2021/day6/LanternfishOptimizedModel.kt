package me.sivieri.aoc2021.day6

class LanternfishOptimizedModel(
    initialValues: List<Int>
) {

    private val counters: MutableMap<Int, Long> = mutableMapOf()

    init {
        val initialMap = initialValues
            .groupingBy { it }
            .eachCount()
            .toMap()
        counters[0] = initialMap[0]?.toLong() ?: 0L
        counters[1] = initialMap[1]?.toLong() ?: 0L
        counters[2] = initialMap[2]?.toLong() ?: 0L
        counters[3] = initialMap[3]?.toLong() ?: 0L
        counters[4] = initialMap[4]?.toLong() ?: 0L
        counters[5] = initialMap[5]?.toLong() ?: 0L
        counters[6] = initialMap[6]?.toLong() ?: 0L
        counters[7] = initialMap[7]?.toLong() ?: 0L
        counters[8] = initialMap[8]?.toLong() ?: 0L
    }

    fun simulate(days: Int): Long {
        (1..days).forEach { day ->
            println("Day $day")
            val zeros = counters[0]!!
            counters[0] = counters[1]!!
            counters[1] = counters[2]!!
            counters[2] = counters[3]!!
            counters[3] = counters[4]!!
            counters[4] = counters[5]!!
            counters[5] = counters[6]!!
            counters[6] = counters[7]!!
            counters[7] = counters[8]!!
            counters[8] = zeros
            counters[6] = counters[6]!! + zeros
        }
        return counters.entries.sumOf { it.value }
    }

}