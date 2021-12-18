package me.sivieri.aoc2021.day17

class Trench(
    private val targetMinX: Int,
    private val targetMaxX: Int,
    private val targetMinY: Int,
    private val targetMaxY: Int
) {

    fun findMaxY(): Int {
        var result = -100000
        for (xv in 0..200) {
            for (yv in 0..200) {
                val newY = testVelocity(xv, yv, 1000)
                if (newY > result) result = newY
            }
        }
        return result
    }

    private fun testVelocity(xs: Int, ys: Int, n: Int): Int {
        var maxY = -100000
        var x = 0
        var y = 0
        var xv = xs
        var yv = ys
        for (i in 0..n) {
            x += xv
            y += yv
            if (y > maxY) maxY = y
            yv--
            if (xv > 0) xv--
            else if (xv < 0) xv++
            if (x in targetMinX..targetMaxX && y in targetMinY..targetMaxY) return maxY
        }
        return 0
    }

}