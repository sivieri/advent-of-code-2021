package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.common.Coordinate3D

class BeaconDetector(
    input: String
) {

    private val scanners: List<Scanner> = input
        .split("\n\n")
        .map { Scanner.fromString(it) }

    fun detect(): Set<Coordinate3D> {
        val solved = mutableListOf(scanners.first())
        var base = scanners.first()
        while (solved.size < scanners.size) {
            scanners
                .forEach { scanner ->
                    if (!solved.contains(scanner)) {
                        val result = base.compareWithScanner(scanner)
                        if (result?.second != null) {
                            base = base.addBeacons(result.first, result.second!!)
                            solved.add(scanner)
                        }
                    }
                }
        }
        return base.beacons
    }

}