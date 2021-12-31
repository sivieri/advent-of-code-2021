package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.common.Coordinate3D

class Reactor {

    private val activeCubes = mutableSetOf<Coordinate3D>()
    private var activeRegions = listOf<Region3D>()

    fun reboot(input: List<String>, limit: Int? = null) {
        val instructions = InstructionParser().parse(input, limit = limit)
        if (limit == null) {
            rebootLarge(instructions)
        }
        else {
            rebootSmall(instructions)
        }
    }

    private fun rebootSmall(instructions: List<Instruction>) {
        instructions
            .forEach { instruction ->
                val cubes = instruction.generateCubes()
                cubes.forEach { cube ->
                    when (instruction.instructionType) {
                        InstructionType.ON -> activeCubes.add(cube)
                        InstructionType.OFF -> activeCubes.remove(cube)
                    }
                }
            }
    }

    private fun rebootLarge(instructions: List<Instruction>) {
        instructions
            .forEachIndexed { index, instruction ->
                println("Instruction ${index + 1}, size ${activeRegions.size}")
                val region = Region3D(
                    Coordinate3D(instruction.minX, instruction.minY, instruction.minZ),
                    Coordinate3D(instruction.maxX + 1, instruction.maxY + 1, instruction.maxZ + 1),
                    instruction.instructionType
                )
                activeRegions = if (activeRegions.isEmpty() && instruction.instructionType == InstructionType.ON) {
                    listOf(region)
                }
                else {
                    val cuboid = if (region.instructionType == InstructionType.ON) listOf(region)
                    else emptyList()
                    activeRegions
                        .flatMap { existing ->
                            existing.combine(region)
                        }
                        .filter { !region.contains(it) }
                        .plus(cuboid)
                }
            }
    }

    fun countActiveCubes(): Long =
        if (activeCubes.isNotEmpty()) activeCubes.size.toLong()
        else activeRegions.sumOf { it.size() }

}