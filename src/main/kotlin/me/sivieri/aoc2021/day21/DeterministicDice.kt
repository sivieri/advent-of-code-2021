package me.sivieri.aoc2021.day21

data class DeterministicDice(
    private var value: Int = 1,
    override var rolls: Int = 0
) : Dice {

    override fun next(): Int {
        rolls++
        if (value > MAX) {
            value = 1
        }
        return value++
    }

    companion object {
        private const val MAX = 100
    }
}
