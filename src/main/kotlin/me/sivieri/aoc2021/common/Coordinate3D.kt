package me.sivieri.aoc2021.common

import kotlin.math.pow
import kotlin.math.abs

data class Coordinate3D(
    val x: Int,
    val y: Int,
    val z: Int
): Comparable<Coordinate3D> {

    override fun compareTo(other: Coordinate3D): Int {
        val thisDistance = distance(ORIGIN)
        val otherDistance = other.distance(ORIGIN)
        return thisDistance.compareTo(otherDistance)
    }

    private fun distance(other: Coordinate3D): Int =
        kotlin.math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toInt()

    fun manhattanDistance(other: Coordinate3D): Int =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    operator fun minus(other: Coordinate3D): Coordinate3D =
        Coordinate3D(
            x - other.x,
            y - other.y,
            z - other.z
        )

    companion object {
        val ORIGIN = Coordinate3D(0, 0, 0)

        fun parseString(s: String, separator: String = ","): Coordinate3D {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3D(x.toInt(), y.toInt(), z.toInt())
        }

    }

}
