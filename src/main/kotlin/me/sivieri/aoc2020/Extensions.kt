package me.sivieri.aoc2020

import java.lang.IllegalArgumentException
import java.math.BigInteger
import java.util.*
import java.util.stream.Stream
import kotlin.math.abs

internal fun <T> Stream<T>.toList(): List<T> =
    this
        .iterator()
        .asSequence()
        .toList()

internal fun <T> List<T>.multiplyBy(f: (T) -> Long): Long =
    this.fold(1) { acc, t ->
        acc * f(t)
    }

internal fun <T> List<T>.head(): T = this.first()

internal fun <T> List<T>.tail(): List<T> = this.subList(1, this.size)

internal fun <T> List<T>.zipWithIndex(): List<Pair<Int, T>> =
    this.mapIndexed { index, t -> Pair(index, t) }

internal fun Boolean.toInt() = if (this) 1 else 0

internal fun BitSet.toList(): List<Int> {
    val res = List(this.size()) { 0 }
    return res
        .mapIndexed { index, _ -> this.get(index).toInt() }
        .reversed()
}

internal fun BitSet.toBinaryString(length: Int): String {
    val list = this.toList()
    return list.subList(list.size - length, list.size).joinToString("")
}

internal fun BitSet.toLong(size: Int): Long {
    val buffer = StringBuffer()
    (size - 1 downTo 0).forEach { index ->
        if (this.get(index)) buffer.append("1")
        else buffer.append("0")
    }
    return BigInteger(buffer.toString(), 2).longValueExact()
}

internal fun <K, V> Map<K, V>.find(f: (K) -> Boolean): V? {
    val key = this
        .keys
        .find(f)
    return this[key]
}

internal fun <T> List<T>.indexOfAll(f: (T) -> Boolean): List<Int> =
    this
        .mapIndexed { index, t -> Pair(index, t) }
        .filter { f(it.second) }
        .map { it.first }

internal fun <T> List<T>.safeSubList(start: Int, end: Int): List<T> {
    if (start < 0 || end < 0) throw IllegalArgumentException("$start < 0 || $end < 0")
    if (start > end) throw IllegalArgumentException("$start > $end")
    if (start >= this.size) return emptyList()
    if (end > this.size) return this.subList(start, this.size)
    return this.subList(start, end)
}

internal fun <T> safeArrayCopy(
    src: Array<T>,
    srcPos: Int,
    dest: Array<T>,
    destPos: Int,
    length: Int
) {
    if (srcPos < 0 || destPos < 0) throw IllegalArgumentException("$srcPos < 0 || $destPos < 0")
    if (srcPos > src.size) throw IllegalArgumentException("$srcPos > ${src.size}")
    if (srcPos >= src.size || destPos >= dest.size) return
    if (srcPos + length > src.size) System.arraycopy(
        src,
        srcPos,
        dest,
        destPos,
        src.size - srcPos
    )
    System.arraycopy(src, srcPos, dest, destPos, length)
}

internal fun <T> Array<T>.shift(
    dest: Array<T>,
    shift: Int
) {
    when {
        shift == 0 -> System.arraycopy(this, 0, dest, 0, this.size)
        shift > 0 -> {
            System.arraycopy(
                this,
                0,
                dest,
                shift,
                this.size - shift
            )
            System.arraycopy(
                this,
                this.size - shift,
                dest,
                0,
                shift
            )
        }
        else -> {
            System.arraycopy(
                this,
                0,
                dest,
                this.size - abs(shift),
                abs(shift)
            )
            System.arraycopy(
                this,
                abs(shift),
                dest,
                0,
                this.size - abs(shift)
            )
        }
    }
}

internal fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

internal fun <T> Array<T>.arrayEquals(other: Array<T>): Boolean {
    if (this.size != other.size) return false
    for (i in this.indices) {
        if (this[i] != other[i]) return false
    }
    return true
}

internal fun <T> List<Array<T>>.indexOfArray(element: Array<T>): Int {
    for (i in this.indices) {
        if (this[i].arrayEquals(element)) return i
    }
    return -1
}

internal fun Array<Array<Char>>.stringRepresentation(): String =
    this.joinToString("\n") { it.joinToString("") }

internal fun String.toMatrix(): Array<Array<Char>> =
    this
        .split("\n")
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
