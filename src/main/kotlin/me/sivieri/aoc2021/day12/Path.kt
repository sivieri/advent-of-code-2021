package me.sivieri.aoc2021.day12

data class Path(
    val path: List<Cave>,
    val visits: Map<Cave, Int>
) {

    fun smallAlreadyVisitedTwice(cave: Cave): Boolean =
        visits.any { it.key.isSmall() && it.value > 1 }
        && visits.containsKey(cave)

    fun addCave(cave: Cave): Path = fromPath(path.plus(cave))

    override fun toString(): String = path.joinToString(",") { it.name }

    companion object {
        private fun fromPath(path: List<Cave>): Path = Path(
            path,
            path.groupingBy { it }.eachCount()
        )
    }

}
