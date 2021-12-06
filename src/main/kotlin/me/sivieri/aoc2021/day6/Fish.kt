package me.sivieri.aoc2021.day6

data class Fish(
    var timer: Int = maxInitialTimer
) {

    fun newDay(): Boolean =
        if (timer == 0) {
            timer = maxTimer
            true
        }
        else {
            timer--
            false
        }

    companion object {
        private const val maxInitialTimer = 8
        private const val maxTimer = 6
    }

}
