package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.common.Coordinate3D

class BeaconDetector(
    input: String
) {

    private val scanners: List<Scanner> = input
        .split("\n\n")
        .map { Scanner.fromString(it) }

    fun detect(): List<Coordinate3D> {
        TODO()
    }

}