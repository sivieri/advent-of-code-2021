package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.common.Coordinate3D
import me.sivieri.aoc2021.crossProduct

class BeaconDetector(
    input: String
) {

    private val scanners: List<Scanner> = input
        .split("\n\n")
        .map { Scanner.fromString(it) }

    fun detect(): Set<Coordinate3D> {
        val initial = scanners.first()
        initial.position = Coordinate3D.ORIGIN
        val solved = mutableListOf(initial)
        var base = initial
        while (solved.size < scanners.size) {
            scanners
                .forEach { scanner ->
                    if (!solved.contains(scanner)) {
                        val result = base.compareWithScanner(scanner)
                        if (result?.second != null) {
                            base = base.addBeacons(result.first, result.second!!)
                            scanner.position = result.second
                            solved.add(scanner)
                        }
                    }
                }
        }
        return base.beacons
    }

    fun findLargestManhattanDistance(): Int {
        detect()
        val positions = scanners.map { it.position!! }
        return positions
            .crossProduct(positions)
            .maxOf { it.first.manhattanDistance(it.second) }
    }

}