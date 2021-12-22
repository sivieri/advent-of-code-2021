package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.common.Coordinate3D

data class Scanner(
    val id: Int,
    val beacons: List<Coordinate3D>
) {

    companion object {
        fun fromString(input: String): Scanner {
            val data = input.split("\n")
            val (_, id) = data.first().trim(' ', '-').split(" ", limit = 2)
            val positions = data.subList(1, data.size).map { Coordinate3D.parseString(it) }
            return Scanner(id.toInt(), positions)
        }
    }

}
