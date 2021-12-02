package me.sivieri.aoc2021.day2

class InstructionParser {

    fun parse(
        instructions: List<String>
    ): Position =
        instructions
            .map { line ->
                val (instruction, value) = line.split(" ", limit = 2)
                Instruction(InstructionType.valueOf(instruction.uppercase()), value.toInt())
            }
            .fold(Position()) { position, instruction ->
                when (instruction.instructionType) {
                    InstructionType.FORWARD -> position.increaseHorizontalPosition(instruction.value)
                    InstructionType.UP -> position.decreaseDepth(instruction.value)
                    InstructionType.DOWN -> position.increaseDepth(instruction.value)
                }
            }

    fun parseWithAim(
        instructions: List<String>
    ): Position =
        instructions
            .map { line ->
                val (instruction, value) = line.split(" ", limit = 2)
                Instruction(InstructionType.valueOf(instruction.uppercase()), value.toInt())
            }
            .fold(Position()) { position, instruction ->
                when (instruction.instructionType) {
                    InstructionType.FORWARD -> position.increaseDepthAndHorizontalPositionWithAim(instruction.value)
                    InstructionType.UP -> position.decreaseAim(instruction.value)
                    InstructionType.DOWN -> position.increaseAim(instruction.value)
                }
            }

}