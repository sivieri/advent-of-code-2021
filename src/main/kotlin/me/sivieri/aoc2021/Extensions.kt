package me.sivieri.aoc2021

internal fun String.toIntList(): List<Int> = this.split(",").map { it.toInt() }