package me.sivieri.aoc2021.day6

class LanternfishModel(
    initialValues: List<Int>
) {

    private val fish: MutableList<Fish> = initialValues.map { Fish(it) }.toMutableList()
    private var day: Int = 0

    fun simulateDay() {
        day++
        println("Day $day")
        val newFish = fish.mapNotNull {
            if (it.newDay()) Fish()
            else null
        }
        fish.addAll(newFish)
    }

    fun fishCount() = fish.size

    override fun toString(): String =
        if (day == 0) {
            "Initial state: ${fish.joinToString(",") { it.timer.toString() } }"
        }
        else {
            "After $day days: ${fish.joinToString(",") { it.timer.toString() } }"
        }
}