package me.sivieri.aoc2021.day18

import java.lang.IllegalArgumentException
import kotlin.math.ceil
import kotlin.math.floor

data class TreeNumber(val value: Int): TreeNumberPair() {
    override fun split(): TreeNumberPair {
        if (value < 10) throw IllegalArgumentException("Split is only for values >= 10: actual value = $value")
        return TreePair(
            TreeNumber(floor(value.toDouble() / 2).toInt()),
            TreeNumber(ceil(value.toDouble() / 2).toInt())
        )
    }
}
