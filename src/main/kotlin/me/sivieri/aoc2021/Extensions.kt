package me.sivieri.aoc2021

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.jgrapht.Graph
import java.lang.IllegalArgumentException
import java.util.Collections.swap

internal fun String.toIntList(): List<Int> = this.split(",").map { it.toInt() }

// https://gist.github.com/dmdrummond/4b1d8a4f024183375f334a5f0a984718
internal fun <T> List<T>.permutations(): List<List<T>> {
    val retVal: MutableList<List<T>> = mutableListOf()

    fun generate(k: Int, list: List<T>) {
        // If only 1 element, just output the array
        if (k == 1) {
            retVal.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1, list)
                if (k % 2 == 0) {
                    swap(list, i, k - 1)
                } else {
                    swap(list, 0, k - 1)
                }
            }
        }
    }

    generate(this.count(), this.toList())
    return retVal
}

internal fun <A, B> cartesianProduct(a: List<A>, b: List<B>): List<Pair<A, B>> =
    a.flatMap { aElement -> b.map { bElement -> aElement to bElement } }

internal fun <A, B> Collection<A>.crossProduct(other: Collection<B>): List<Pair<A, B>> =
    this.flatMap { aElement -> other.map { bElement -> aElement to bElement } }

internal fun <T> cartesianProductOfThree(
    a: Collection<T>,
    b: Collection<T>,
    c: Collection<T>,
): List<Triple<T, T, T>> =
    a.flatMap { aElement ->
        b.flatMap { bElement ->
            c.map { cElement -> Triple(aElement, bElement, cElement) }
        }
    }

internal fun <T> List<T>.zipWithIndex(): List<Pair<Int, T>> =
    this.mapIndexed { index, t -> Pair(index, t) }

internal fun <T> List<T>.multiplyBy(f: (T) -> Long): Long =
    this.fold(1) { acc, t ->
        acc * f(t)
    }

internal fun <T> List<T>.getMiddleElement(): T = this[(this.size - 1) / 2]

internal fun <T> Array<Array<T>>.stringRepresentation(cellSeparator: String, f: (T) -> String): String =
    this.joinToString("\n") { it.joinToString(cellSeparator) { f(it) }  }

internal fun String.isLowerCase(): Boolean = this.all { it in 'a'..'z' }

internal fun <V, E> Graph<V, E>.getOtherVertex(vertex: V, edge: E): V =
    when (vertex) {
        this.getEdgeSource(edge) -> this.getEdgeTarget(edge)
        this.getEdgeTarget(edge) -> this.getEdgeSource(edge)
        else -> throw IllegalArgumentException("Vertex $vertex is not connected to edge $edge")
    }

internal fun <K, V> Map<K, V>.combineWith(otherMap: Map<K, V>, f: (v1: V?, v2: V?) -> V): Map<K, V> =
    (this.keys + otherMap.keys).associateWith { key ->
        f(this[key], otherMap[key])
    }

internal suspend fun <K, V, R> Map<K, V>.pmap(f: suspend (Map.Entry<K, V>) -> R): List<R> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}
