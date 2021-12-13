package me.sivieri.aoc2021.day13

import me.sivieri.aoc2021.common.Coordinate2D
import me.sivieri.aoc2021.stringRepresentation

class Origami(
    input: String
) {

    var image: Array<Array<Char>>
    val instructions: List<FoldInstruction>

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
        image = Array(maxX + 1) { Array(maxY + 1) { EMPTY } }
        coordinates.forEach { (x, y) ->
            image[x][y] = DOT
        }
        instructions = instructionLines
            .split("\n")
            .filterNot { it.isBlank() }
            .map { line ->
                val (coordinate, value) = line.substring(11).split("=", limit = 2)
                FoldInstruction(CoordinateName.valueOf(coordinate.uppercase()).invert(), value.toInt())
            }
    }

    fun applyFold(fold: FoldInstruction) {
        val (part1, part2) = when (fold.coordinateName) {
            CoordinateName.X -> {
                image.copyOfRange(0, fold.value) to reverseOnX(image.copyOfRange(fold.value + 1, image.size))
            }
            CoordinateName.Y -> {
                val pairs = image
                    .map { row ->
                        row.copyOfRange(0, fold.value) to row.copyOfRange(fold.value + 1, row.size)
                    }
                pairs.map { it.first }.toTypedArray() to reverseOnY(pairs.map { it.second }.toTypedArray())
            }
        }
        val (rebalancedPart1, rebalancedPart2) = rebalanceParts(part1, part2, fold)
        val foldResult = rebalancedPart1
            .zip(rebalancedPart2)
            .map { (row1, row2) ->
                row1
                    .zip(row2)
                    .map { (cell1, cell2) ->
                        if (cell1 == DOT || cell2 == DOT) DOT
                        else EMPTY
                    }
                    .toTypedArray()
            }
            .toTypedArray()
        image = foldResult
    }

    private fun rebalanceParts(
        part1: Array<Array<Char>>,
        part2: Array<Array<Char>>,
        fold: FoldInstruction
    ): Pair<Array<Array<Char>>, Array<Array<Char>>> = when {
        fold.coordinateName == CoordinateName.X && fold.value > (image.size / 2) -> {
            val additionalLinesCount = fold.value * 2 - (image.size - 1)
            val additionalLines = Array(additionalLinesCount) { Array(part2[0].size) { EMPTY } }
            Pair(
                part1,
                additionalLines + part2
            )
        }
        fold.coordinateName == CoordinateName.X && fold.value == (image.size / 2) -> Pair(part1, part2)
        fold.coordinateName == CoordinateName.X && fold.value < (image.size / 2) -> {
            val additionalLinesCount = (image.size - 1) - fold.value * 2
            val additionalLines = Array(additionalLinesCount) { Array(part2[0].size) { EMPTY } }
            Pair(
                additionalLines + part1,
                part2
            )
        }
        fold.coordinateName == CoordinateName.Y && fold.value > (image[0].size / 2) -> {
            val additionalLinesCount = fold.value * 2 - (image[0].size - 1)
            val additionalPart2 = part2.map { row ->
                Array(additionalLinesCount) { EMPTY } + row
            }.toTypedArray()
            Pair(
                part1,
                additionalPart2
            )
        }
        fold.coordinateName == CoordinateName.Y && fold.value == (image[0].size / 2) -> Pair(part1, part2)
        else -> { // fold.coordinateName == CoordinateName.Y && fold.value < (image[0].size / 2)
            val additionalLinesCount = (image.size - 1) - fold.value * 2
            val additionalPart1 = part1.map { row ->
                Array(additionalLinesCount) { EMPTY } + row
            }.toTypedArray()
            Pair(
                additionalPart1,
                part2
            )
        }
    }

    private fun reverseOnX(image: Array<Array<Char>>): Array<Array<Char>> {
        val result = Array(image.size) { Array(image[0].size) { EMPTY } }
        image.indices.forEach { x ->
            image[x].indices.forEach { y ->
                result[image.size - x - 1][y] = image[x][y]
            }
        }
        return result
    }

    private fun reverseOnY(image: Array<Array<Char>>): Array<Array<Char>> {
        val result = Array(image.size) { Array(image[0].size) { EMPTY } }
        image.indices.forEach { x ->
            image[x].indices.forEach { y ->
                result[x][image[x].size - y - 1] = image[x][y]
            }
        }
        return result
    }

    fun countDots(): Int = image.sumOf { it.count { it == DOT } }

    fun getBoard(): String = image.stringRepresentation("") { it.toString() }

    override fun toString(): String =
        "${getBoard()}\n\n${instructions.joinToString(",")}"

    companion object {
        private const val EMPTY = ' '
        private const val DOT = '█'
    }
}