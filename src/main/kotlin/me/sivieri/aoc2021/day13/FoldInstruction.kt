package me.sivieri.aoc2021.day13

data class FoldInstruction(
    val coordinateName: CoordinateName,
    val value: Int
) {
    override fun toString(): String = "$coordinateName=$value"
}

enum class CoordinateName {
    X, Y;

    fun invert(): CoordinateName = when (this) {
        X -> Y
        Y -> X
    }
}
