package me.sivieri.aoc2021.day11

import me.sivieri.aoc2021.common.Coordinate2D
import me.sivieri.aoc2021.stringRepresentation

class OctopusesModel(data: List<String>) {

    private val octopusesEnergyLevels = data
        .map { line ->
            line
                .toList()
                .map { it.toString().toInt() }
                .toTypedArray()
        }
        .toTypedArray()
    private val size = data.size

    fun performStep(): Int {
        octopusesEnergyLevels.indices.forEach { x ->
            octopusesEnergyLevels[x].indices.forEach { y ->
                octopusesEnergyLevels[x][y] = octopusesEnergyLevels[x][y] + 1
            }
        }
        var flashes: Int
        val flashed = mutableListOf<Coordinate2D>()
        do {
            flashes = flashed.size
            val (currentlyFlashed, neighbors) = performFlash(flashed.toSet())
            flashed.addAll(currentlyFlashed)
            neighbors.forEach { octopusesEnergyLevels[it.key.x][it.key.y] += it.value }
        } while (flashed.size != flashes)
        flashed.forEach { octopusesEnergyLevels[it.x][it.y] = 0 }
        return flashes
    }

    private fun performFlash(
        alreadyFlashed: Set<Coordinate2D>
    ): Pair<List<Coordinate2D>, Map<Coordinate2D, Int>> {
        val readyToFlash = octopusesEnergyLevels.indices
            .flatMap { x ->
                octopusesEnergyLevels[x].indices.mapNotNull { y ->
                    if (octopusesEnergyLevels[x][y] > FLASH_LIMIT) Coordinate2D(x, y)
                    else null
                }
            }
            .subtract(alreadyFlashed)
        val neighbors = mutableMapOf<Coordinate2D, Int>()
        readyToFlash.forEach {
            findNeighbors(it).forEach {
                if (neighbors.containsKey(it)) neighbors[it] = neighbors[it]!! + 1
                else neighbors[it] = 1
            }
        }
        return Pair(readyToFlash.toList(), neighbors.toMap())
    }

    private fun findNeighbors(coordinates: Coordinate2D): List<Coordinate2D> = listOf(
        Coordinate2D(coordinates.x + 1, coordinates.y - 1),
        Coordinate2D(coordinates.x + 1, coordinates.y),
        Coordinate2D(coordinates.x + 1, coordinates.y + 1),
        Coordinate2D(coordinates.x - 1, coordinates.y - 1),
        Coordinate2D(coordinates.x - 1, coordinates.y),
        Coordinate2D(coordinates.x - 1, coordinates.y + 1),
        Coordinate2D(coordinates.x, coordinates.y - 1),
        Coordinate2D(coordinates.x, coordinates.y + 1)
    ).filter { it.x in (0 until size) && it.y in (0 until size) }

    fun performNSteps(n: Int): Int = (1..n).sumOf { performStep() }

    fun stringRepresentation(): String = octopusesEnergyLevels.stringRepresentation(cellSeparator = "") { it.toString() }

    override fun toString(): String = octopusesEnergyLevels.stringRepresentation(cellSeparator = " ") { it.toString().padStart(2, ' ') }

    companion object {
        private const val FLASH_LIMIT = 9
    }

}