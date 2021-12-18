package me.sivieri.aoc2021.day18

sealed class TreeNumberPair {
    operator fun plus(other: TreeNumberPair): TreeNumberPair = TreePair(this, other)

    open fun split(): TreeNumberPair = throw NotImplementedError("Not used")

    open fun reduce(): TreeNumberPair = throw NotImplementedError("Not used")

    companion object {
        fun treeFromString(string: String): TreeNumberPair {
            val stack = ArrayDeque<TreeNumberParserStatus>()
            string.forEach { c ->
                when (c) {
                    '[' -> stack.addFirst(TreeNumberBeginStatus)
                    ']' -> {
                        val second = stack.removeFirst()
                        val first = stack.removeFirst()
                        val ending = stack.removeFirst()
                        if (
                            ending !is TreeNumberBeginStatus
                            || first is TreeNumberBeginStatus
                            || second is TreeNumberBeginStatus
                        )
                            throw IllegalStateException("Invalid state for the stack")
                        stack.addFirst(PairStatus(TreePair(first.getTreeNumber(), second.getTreeNumber())))
                    }
                    ',' -> { }
                    else -> stack.addFirst(NumberStatus(TreeNumber(c.toString().toInt())))
                }
            }
            val result = stack.removeFirst()
            if (stack.size > 0) throw IllegalStateException("More than one element at the end of parsing")
            if (result is TreeNumberBeginStatus) throw IllegalStateException("Final element is a beginning")
            return result.getTreeNumber()
        }
    }
}
