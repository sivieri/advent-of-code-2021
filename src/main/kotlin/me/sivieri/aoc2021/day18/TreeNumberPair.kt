package me.sivieri.aoc2021.day18

import kotlin.math.ceil
import kotlin.math.floor

sealed class TreeNumberPair(
    protected var parent: TreeNumberPair? = null
) {
    operator fun plus(other: TreeNumberPair): TreeNumberPair = TreePair(this, other)

    @Suppress("KotlinConstantConditions")
    fun reduce(): TreeNumberPair {
        var pairToExplode: TreePair?
        var numberToSplit: TreeNumber?
        var root = this
        do {
            pairToExplode = searchPairToExplode(root)
            if (pairToExplode != null) {
                val parent = pairToExplode.parent!! as TreePair
                if (pairToExplode == parent.left) parent.left = pairToExplode.explode()
                if (pairToExplode == parent.right) parent.right = pairToExplode.explode()
                numberToSplit = null
            }
            else {
                numberToSplit = searchNumberToSplit(root)
                if (numberToSplit != null) {
                    if (numberToSplit.parent != null) {
                        val parent = numberToSplit.parent!! as TreePair
                        if (numberToSplit == parent.left) parent.left = numberToSplit.split()
                        if (numberToSplit == parent.right) parent.right = numberToSplit.split()
                    }
                    else {
                        root = numberToSplit.split()
                    }

                }
            }
        } while (pairToExplode != null && numberToSplit != null)
        return root
    }

    fun split(): TreeNumberPair {
        if (this !is TreeNumber) throw IllegalArgumentException("Only numbers can be split")
        if (value < 10) throw IllegalArgumentException("Split is only for values >= 10: actual value = $value")
        return TreePair(
            TreeNumber(floor(value.toDouble() / 2).toInt()),
            TreeNumber(ceil(value.toDouble() / 2).toInt())
        )
    }

    fun explode(): TreeNumberPair {
        if (this !is TreePair) throw IllegalArgumentException("Only pairs can be exploded")
        if (this.left !is TreeNumber || this.right !is TreeNumber) throw IllegalArgumentException("Only pairs of numbers can be exploded")
        val firstLeft = searchLeft(this)
        val firstRight = searchRight(this)
        if (firstLeft != null) {
            if (firstLeft.left is TreeNumber) {
                firstLeft.left = TreeNumber((firstLeft.left as TreeNumber).value + (this.left as TreeNumber).value)
            }
            if (firstLeft.right is TreeNumber) {
                firstLeft.right = TreeNumber((firstLeft.right as TreeNumber).value + (this.left as TreeNumber).value)
            }
        }
        if (firstRight != null) {
            if (firstRight.left is TreeNumber) {
                firstRight.left = TreeNumber((firstRight.left as TreeNumber).value + (this.right as TreeNumber).value)
            }
            if (firstRight.right is TreeNumber) {
                firstRight.right = TreeNumber((firstRight.right as TreeNumber).value + (this.right as TreeNumber).value)
            }
        }
        return TreeNumber(0)
    }

    companion object {
        private fun searchPairToExplode(root: TreeNumberPair): TreePair? {
            TODO("Not yet implemented")
        }

        private fun searchNumberToSplit(root: TreeNumberPair): TreeNumber? {
            TODO("Not yet implemented")
        }

        private fun searchLeft(pair: TreePair): TreePair? {
            var current = pair
            while (current.parent != null) {
                if ((current.parent as TreePair).left == current) {
                    current = (current.parent as TreePair)
                }
                else return current.parent as TreePair
            }
            return null
        }

        private fun searchRight(pair: TreePair): TreePair? {
            var current = pair
            while (current.parent != null) {
                if ((current.parent as TreePair).right == current) {
                    current = (current.parent as TreePair)
                }
                else return current.parent as TreePair
            }
            return null
        }

        fun fromString(string: String): TreeNumberPair {
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
                        val treePair = TreePair(first.getTreeNumberPair(), second.getTreeNumberPair())
                        treePair.left.parent = treePair
                        treePair.right.parent = treePair
                        stack.addFirst(PairStatus(treePair))
                    }
                    ',' -> { }
                    else -> stack.addFirst(NumberStatus(TreeNumber(c.toString().toInt())))
                }
            }
            val result = stack.removeFirst()
            if (stack.size > 0) throw IllegalStateException("More than one element at the end of parsing")
            if (result is TreeNumberBeginStatus) throw IllegalStateException("Final element is a beginning")
            return result.getTreeNumberPair()
        }
    }
}
