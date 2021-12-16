package me.sivieri.aoc2021.day16

sealed class Packet(
    val version: Int,
    val typeId: Int
) {

    abstract fun versionSum(): Int

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
    val value: Int
): Packet(version, LITERAL_VALUE_TYPE_ID) {

    override fun versionSum(): Int = version

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
        result = 31 * result + value
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

    override fun versionSum(): Int = version + subPackets.sumOf { it.version }

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
