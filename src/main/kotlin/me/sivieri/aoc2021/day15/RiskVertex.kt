package me.sivieri.aoc2021.day15

import me.sivieri.aoc2021.common.Coordinate2D

// no data class because we don't want to override equals and hashCode for all attributes
class RiskVertex(
    val coordinates: Coordinate2D,
    val weight: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RiskVertex

        if (coordinates != other.coordinates) return false

        return true
    }

    override fun hashCode(): Int {
        return coordinates.hashCode()
    }

    override fun toString(): String {
        return "RiskVertex(coordinates=$coordinates, weight=$weight)"
    }

}
