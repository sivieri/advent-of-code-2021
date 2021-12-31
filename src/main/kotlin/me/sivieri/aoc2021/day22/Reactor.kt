package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.common.Coordinate3D

class Reactor {

    private val activeCubes = mutableSetOf<Coordinate3D>()

    fun reboot(input: List<String>, limit: Int? = null) {
        val instructions = InstructionParser().parse(input, limit = limit)
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

    fun countActiveCubes() = activeCubes.size

}