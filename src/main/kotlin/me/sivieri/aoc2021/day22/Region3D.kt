package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.common.Coordinate3D
import kotlin.math.abs

data class Region3D(
    val minCorner: Coordinate3D,
    val maxCorner: Coordinate3D,
    val instructionType: InstructionType
) {

    fun contains(other: Region3D): Boolean =
        other.minCorner.x >= minCorner.x && other.maxCorner.x <= maxCorner.x &&
        other.minCorner.y >= minCorner.y && other.maxCorner.y <= maxCorner.y &&
        other.minCorner.z >= minCorner.z && other.maxCorner.z <= maxCorner.z

    fun combine(other: Region3D): List<Region3D> =
        splitX(other.minCorner.x)
            .flatMap { it.splitX(other.maxCorner.x) }
            .flatMap { it.splitY(other.minCorner.y) }
            .flatMap { it.splitY(other.maxCorner.y) }
            .flatMap { it.splitZ(other.minCorner.z) }
            .flatMap { it.splitZ(other.maxCorner.z) }

    private fun splitX(x: Int): List<Region3D> =
        if (x > minCorner.x && x < maxCorner.x) listOf(
            Region3D(
                minCorner,
                maxCorner.copy(x = x),
                instructionType
            ),
            Region3D(
                minCorner.copy(x = x),
                maxCorner,
                instructionType
            )
        )
        else listOf(this)

    private fun splitY(y: Int): List<Region3D> =
        if (y > minCorner.y && y < maxCorner.y) listOf(
            Region3D(
                minCorner,
                maxCorner.copy(y = y),
                instructionType
            ),
            Region3D(
                minCorner.copy(y = y),
                maxCorner,
                instructionType
            )
        )
        else listOf(this)

    private fun splitZ(z: Int): List<Region3D> =
        if (z > minCorner.z && z < maxCorner.z) listOf(
            Region3D(
                minCorner,
                maxCorner.copy(z = z),
                instructionType
            ),
            Region3D(
                minCorner.copy(z = z),
                maxCorner,
                instructionType
            )
        )
        else listOf(this)

    fun size(): Long = abs(maxCorner.x - minCorner.x).toLong() *
            abs(maxCorner.y - minCorner.y).toLong() *
            abs(maxCorner.z - minCorner.z).toLong()

    override fun toString(): String =
        "${instructionType.name.lowercase()} x=${minCorner.x}..${maxCorner.x - 1},y=${minCorner.y}..${maxCorner.y - 1},x=${minCorner.z}..${maxCorner.z - 1}"
}
