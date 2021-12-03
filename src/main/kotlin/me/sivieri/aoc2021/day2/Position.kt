package me.sivieri.aoc2021.day2

data class Position(
    val depth: Int = 0,
    val horizontalPosition: Int = 0,
    val aim: Int = 0
) {

    fun increaseDepth(value: Int) = Position(depth + value, horizontalPosition)

    fun decreaseDepth(value: Int) = Position(depth - value, horizontalPosition)

    fun increaseHorizontalPosition(value: Int) = Position(depth, horizontalPosition + value)

    fun increaseAim(value: Int) = Position(depth, horizontalPosition, aim + value)

    fun decreaseAim(value: Int) = Position(depth, horizontalPosition, aim - value)

    fun increaseDepthAndHorizontalPositionWithAim(value: Int) = Position(depth + aim * value, horizontalPosition + value, aim)

}
