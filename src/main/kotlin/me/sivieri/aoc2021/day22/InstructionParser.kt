package me.sivieri.aoc2021.day22

class InstructionParser {

    fun parse(data: List<String>, limit: Int? = null): List<Instruction> =
        data
            .mapNotNull { line ->
                val (instructionType, coordinates) = line.split(" ", limit = 2)
                val (x, y, z) = coordinates.split(",", limit = 3)
                val (x1, x2) = x.split("=")[1].split("..", limit = 2)
                val (y1, y2) = y.split("=")[1].split("..", limit = 2)
                val (z1, z2) = z.split("=")[1].split("..", limit = 2)
                if (limit != null) {
                    if (x1.toInt() < -limit || x2.toInt() > limit ||
                        y1.toInt() < -limit || y2.toInt() > limit ||
                        z1.toInt() < -limit || z2.toInt() > limit) null
                    else Instruction(
                        InstructionType.valueOf(instructionType.uppercase()),
                        x1.toInt(), x2.toInt(),
                        y1.toInt(), y2.toInt(),
                        z1.toInt(), z2.toInt()
                    )
                }
                else {
                    Instruction(
                        InstructionType.valueOf(instructionType.uppercase()),
                        x1.toInt(), x2.toInt(),
                        y1.toInt(), y2.toInt(),
                        z1.toInt(), z2.toInt()
                    )
                }
            }

}