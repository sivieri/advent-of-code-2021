package me.sivieri.aoc2021.day25

import me.sivieri.aoc2021.common.Coordinate2D
import java.lang.IllegalArgumentException

class FloorBed(input: String) {

    private var board = input
        .split("\n")
        .filterNot { it.isBlank() }
        .map { line -> line.toList().toTypedArray() }
        .toTypedArray()

    fun evolve(): Int {
        var steps = 0
        var moved = true
        while (moved) {
            steps++
            println("Step $steps")
            val (update, changes) = calculateUpdate(board)
            moved = changes
            board = update
        }

        return steps
    }

    companion object {
        private const val EMPTY = '.'

        fun calculateUpdate(board: Array<Array<Char>>): Pair<Array<Array<Char>>, Boolean> {
            var moved = false
            val xmax = board.size
            val ymax = board[0].size
            val firstUpdate = Array(xmax) { Array(ymax) { EMPTY } }
            val secondUpdate = Array(xmax) { Array(ymax) { EMPTY } }
            board.indices.forEach { x ->
                board[x].indices.forEach { y ->
                    val piece = board[x][y]
                    if (piece == '>') {
                        val destination = findDestination(Coordinate2D(x, y), piece, xmax, ymax)
                        if (board[destination.x][destination.y] == EMPTY) {
                            firstUpdate[destination.x][destination.y] = board[x][y]
                            firstUpdate[x][y] = EMPTY
                            moved = true
                        }
                        else {
                            firstUpdate[x][y] = piece
                        }
                    }
                    else if (piece == 'v') {
                        firstUpdate[x][y] = piece
                    }
                }
            }
            firstUpdate.indices.forEach { x ->
                firstUpdate[x].indices.forEach { y ->
                    val piece = firstUpdate[x][y]
                    if (piece == 'v') {
                        val destination = findDestination(Coordinate2D(x, y), piece, xmax, ymax)
                        if (firstUpdate[destination.x][destination.y] == EMPTY) {
                            secondUpdate[destination.x][destination.y] = firstUpdate[x][y]
                            secondUpdate[x][y] = EMPTY
                            moved = true
                        }
                        else {
                            secondUpdate[x][y] = piece
                        }
                    }
                    else if (piece == '>') {
                        secondUpdate[x][y] = piece
                    }
                }
            }
            return Pair(secondUpdate, moved)
        }

        private fun findDestination(
            source: Coordinate2D,
            piece: Char,
            xmax: Int,
            ymax: Int
        ): Coordinate2D {
            val nextX = (source.x + 1) % xmax
            val nextY = (source.y + 1) % ymax
            return when (piece) {
                'v' -> Coordinate2D(nextX, source.y)
                '>' -> Coordinate2D(source.x, nextY)
                else -> throw IllegalArgumentException("Piece $piece does not move")
            }
        }
    }

}