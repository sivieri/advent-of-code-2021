package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.common.Coordinate3D

class Scanner(
    val id: Int,
    val beacons: List<Coordinate3D>
) {

    fun generateAllCombinations(): List<Scanner> =
        listOf(
            generateInversion(this, mapOf(1 to 1, 2 to 2, 3 to 3)),
            generateInversion(this, mapOf(1 to 1, 2 to 3, 3 to 2)),
            generateInversion(this, mapOf(1 to 2, 2 to 1, 3 to 3)),
            generateInversion(this, mapOf(1 to 2, 2 to 3, 3 to 1)),
            generateInversion(this, mapOf(1 to 3, 2 to 1, 3 to 2)),
            generateInversion(this, mapOf(1 to 3, 2 to 2, 3 to 1))
        ).flatMap { inversion ->
            listOf(
                generateSign(inversion, 1, 1, 1),
                generateSign(inversion, 1, 1, -1),
                generateSign(inversion, 1, -1, 1),
                generateSign(inversion, 1, -1, -1),
                generateSign(inversion, -1, 1, 1),
                generateSign(inversion, -1, 1, -1),
                generateSign(inversion, -1, -1, 1),
                generateSign(inversion, -1, -1, -1)
            )
        }

    fun compareWithScanner(other: Scanner): Pair<Scanner, Coordinate3D?>? =
        other
            .generateAllCombinations()
            .map { combination ->
                val count = beacons
                    .flatMap { beacon ->
                        combination
                            .beacons
                            .map { otherBeacon ->
                                beacon - otherBeacon
                            }
                    }
                    .groupingBy { it }
                    .eachCount()
                combination to count
                    .entries
                    .firstOrNull { it.value >= COMPARISON_N }
                    ?.key
            }
            .firstOrNull() { it.second != null }

    fun addBeacons(scanner: Scanner, point: Coordinate3D): Scanner =
        Scanner(
            id,
            beacons + scanner
                .beacons
                .map { beacon ->
                    Coordinate3D(
                        beacon.x + point.x,
                        beacon.y + point.y,
                        beacon.z + point.z,
                    )
                }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scanner

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String = id.toString()

    companion object {
        private const val COMPARISON_N = 12

        fun fromString(input: String): Scanner {
            val data = input.split("\n")
            val (_, id) = data.first().trim(' ', '-').split(" ", limit = 2)
            val positions = data.subList(1, data.size).map { Coordinate3D.parseString(it) }
            return Scanner(id.toInt(), positions)
        }

        private fun generateInversion(scanner: Scanner, inversion: Map<Int, Int>): Scanner =
            Scanner(
                scanner.id,
                scanner.beacons.map { beacon ->
                    val data = mapOf(1 to beacon.x, 2 to beacon.y, 3 to beacon.z)
                    Coordinate3D(
                        data[inversion[1]]!!,
                        data[inversion[2]]!!,
                        data[inversion[3]]!!,
                    )
                }
            )

        private fun generateSign(scanner: Scanner, x: Int, y: Int, z: Int): Scanner =
            Scanner(
                scanner.id,
                scanner.beacons.map { beacon ->
                    Coordinate3D(beacon.x * x, beacon.y * y, beacon.z * z)
                }
            )
    }
}
