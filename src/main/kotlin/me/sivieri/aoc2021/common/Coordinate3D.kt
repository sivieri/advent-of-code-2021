package me.sivieri.aoc2021.common

data class Coordinate3D(
    val x: Int,
    val y: Int,
    val z: Int
) {

    companion object {

        fun parseString(s: String, separator: String = ","): Coordinate3D {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3D(x.toInt(), y.toInt(), z.toInt())
        }

    }

}
