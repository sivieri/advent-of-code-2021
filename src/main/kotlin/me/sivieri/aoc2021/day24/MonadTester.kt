package me.sivieri.aoc2021.day24

class MonadTester(input: List<String>) {

    private val monadInterpreter = MonadInterpreter(input)

    fun searchNumber(): Long {
        val initial = List(14) { 9 }.joinToString("").toLong()
        var z = -1
        var current = initial
        var iteration = 1L
        while (z != 0) {
            if (iteration % 100000L == 0L) println("Iteration $iteration (current: $current)")
            val res = monadInterpreter.run(current)
            current = prepareNext(current)
            z = res["z"]!!
            iteration++
        }
        return current
    }

    private fun prepareNext(current: Long): Long {
        var next = current - 1
        while (next.toString().contains('0')) {
            next--
        }
        return next
    }

}