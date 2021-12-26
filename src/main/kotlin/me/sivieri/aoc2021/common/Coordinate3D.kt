package me.sivieri.aoc2021.common

import kotlin.math.pow

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

    fun distance(other: Coordinate3D): Int =
        kotlin.math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toInt()

    operator fun minus(other: Coordinate3D): Coordinate3D =
        Coordinate3D(
            x - other.x,
            y - other.y,
            z - other.z
        )

    companion object {
        private val ORIGIN = Coordinate3D(0, 0, 0)

        fun parseString(s: String, separator: String = ","): Coordinate3D {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3D(x.toInt(), y.toInt(), z.toInt())
        }

    }

}
