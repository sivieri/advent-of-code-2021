package me.sivieri.aoc2021.day17

import me.sivieri.aoc2021.common.Coordinate2D

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
                val newY = testVelocity(xv, yv, 1000).first
                if (newY > result) result = newY
            }
        }
        return result
    }

    fun findAllCouplesCount(): Long {
        var count = 0L
        for (xv in 0..1000) {
            for (yv in targetMinY..1000) {
                val newY = testVelocity(xv, yv, 10000).second
                if (newY != -1) count++
            }
        }
        return count
    }

    private fun testVelocity(xs: Int, ys: Int, n: Int): Pair<Int, Int> {
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
            if (x in targetMinX..targetMaxX && y in targetMinY..targetMaxY) return Pair(maxY, i)
        }
        return Pair(0, -1)
    }

}