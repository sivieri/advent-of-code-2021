package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.common.Coordinate3D

enum class InstructionType {
    ON, OFF;
}

data class Instruction(
    val instructionType: InstructionType,
    val minX: Int,
    val maxX: Int,
    val minY: Int,
    val maxY: Int,
    val minZ: Int,
    val maxZ: Int
) {

    fun generateCubes(limit: Int? = null): List<Coordinate3D> =
        (minX .. maxX).flatMap { x ->
            (minY .. maxY).flatMap { y ->
                (minZ .. maxZ).mapNotNull { z ->
                    if (limit != null) {
                        if (x < -limit || x > limit ||
                            y < -limit || y > limit ||
                            z < -limit || z > limit) null
                        else Coordinate3D(x, y, z)
                    }
                    else {
                        Coordinate3D(x, y, z)
                    }
                }
            }
        }

}
