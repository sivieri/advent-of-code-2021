package me.sivieri.aoc2021.day13

import me.sivieri.aoc2021.common.Coordinate2D
import me.sivieri.aoc2021.stringRepresentation

class Origami(
    input: String
) {

    private val image: Array<Array<Char>>
    private val instructions: List<FoldInstruction>

    init {
        val (matrix, instructionLines) = input.split("\n\n", limit = 2)
        val coordinates = matrix
            .split("\n")
            .map { line ->
                val (y, x) = line.split(",", limit = 2)
                Coordinate2D(x.toInt(), y.toInt())
            }
        val maxX = coordinates.maxOf { it.x }
        val maxY = coordinates.maxOf { it.y }
        image = Array(maxX + 1) { Array(maxY + 1) { '.' } }
        coordinates.forEach { (x, y) ->
            image[x][y] = '#'
        }
        instructions = instructionLines
            .split("\n")
            .map { line ->
                val (coordinate, value) = line.substring(11).split("=", limit = 2)
                FoldInstruction(CoordinateName.valueOf(coordinate.uppercase()).invert(), value.toInt())
            }
    }

    override fun toString(): String =
        "${image.stringRepresentation("") { it.toString() }}\n\n${instructions.joinToString(",")}"
}