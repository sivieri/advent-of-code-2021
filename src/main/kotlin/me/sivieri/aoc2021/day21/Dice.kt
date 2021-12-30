package me.sivieri.aoc2021.day21

interface Dice {
    var rolls: Int
    fun next(): Int
}