package me.sivieri.aoc2021.day18

object Null: TreeNumberPair()

sealed interface TreeNumberParserStatus {
    fun getTreeNumber(): TreeNumberPair
}

data class NumberStatus(val treeNumber: TreeNumber): TreeNumberParserStatus {
    override fun getTreeNumber(): TreeNumberPair = treeNumber
}

data class PairStatus(val treePair: TreePair): TreeNumberParserStatus {
    override fun getTreeNumber(): TreeNumberPair = treePair
}

object TreeNumberBeginStatus : TreeNumberParserStatus {
    override fun getTreeNumber(): TreeNumberPair = Null
}
