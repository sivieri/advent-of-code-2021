package me.sivieri.aoc2021.day16

import me.sivieri.aoc2021.multiplyBy
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

sealed class Packet(
    val version: Int,
    val typeId: Int
) {

    abstract fun versionSum(): Int

    abstract fun applyOperation(): Long

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Packet

        if (version != other.version) return false
        if (typeId != other.typeId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = version
        result = 31 * result + typeId
        return result
    }

    override fun toString(): String {
        return "Packet(version=$version, typeId=$typeId)"
    }

}

class LiteralValue(
    version: Int,
    val value: Long
): Packet(version, LITERAL_VALUE_TYPE_ID) {

    override fun versionSum(): Int = version

    override fun applyOperation(): Long = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as LiteralValue

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    override fun toString(): String {
        return "LiteralValue(version=$version, typeId=$typeId, value=$value)"
    }

    companion object {
        const val LITERAL_VALUE_TYPE_ID = 4
    }
}

class Operator(
    version: Int,
    typeId: Int,
    val subPackets: List<Packet>
): Packet(version, typeId) {

    override fun versionSum(): Int = version + subPackets.sumOf { it.versionSum() }

    override fun applyOperation(): Long = when (typeId) {
        0 -> subPackets.sumOf { it.applyOperation() }
        1 -> subPackets.multiplyBy { it.applyOperation() }
        2 -> subPackets.minOf { it.applyOperation() }
        3 -> subPackets.maxOf { it.applyOperation() }
        5 -> {
            if (subPackets.size != 2) throw IllegalArgumentException("GT operator needs two subpackets, not ${subPackets.size}")
            if (subPackets[0].applyOperation() > subPackets[1].applyOperation()) 1
            else 0
        }
        6 -> {
            if (subPackets.size != 2) throw IllegalArgumentException("LT operator needs two subpackets, not ${subPackets.size}")
            if (subPackets[0].applyOperation() < subPackets[1].applyOperation()) 1
            else 0
        }
        7 -> {
            if (subPackets.size != 2) throw IllegalArgumentException("EQ operator needs two subpackets, not ${subPackets.size}")
            if (subPackets[0].applyOperation() == subPackets[1].applyOperation()) 1
            else 0
        }
        else -> throw IllegalArgumentException("Type ID $typeId unknown")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as Operator

        if (subPackets != other.subPackets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + subPackets.hashCode()
        return result
    }

    override fun toString(): String {
        return "Operator(version=$version, typeId=$typeId, subPackets=$subPackets)"
    }

}
