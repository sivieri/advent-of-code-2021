package me.sivieri.aoc2021.day18

class TreeNumber(val value: Int): TreeNumberPair()

class TreePair(
    var left: TreeNumberPair,
    var right: TreeNumberPair
): TreeNumberPair()

object Null: TreeNumberPair()

sealed interface TreeNumberParserStatus {
    fun getTreeNumberPair(): TreeNumberPair
}

data class NumberStatus(val treeNumber: TreeNumber): TreeNumberParserStatus {
    override fun getTreeNumberPair(): TreeNumberPair = treeNumber
}

data class PairStatus(val treePair: TreePair): TreeNumberParserStatus {
    override fun getTreeNumberPair(): TreeNumberPair = treePair
}

object TreeNumberBeginStatus : TreeNumberParserStatus {
    override fun getTreeNumberPair(): TreeNumberPair = Null
}
