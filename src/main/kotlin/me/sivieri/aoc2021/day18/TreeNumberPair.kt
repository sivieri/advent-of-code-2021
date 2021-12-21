package me.sivieri.aoc2021.day18

import kotlin.math.ceil
import kotlin.math.floor

sealed class TreeNumberPair(
    protected var parent: TreeNumberPair? = null
) {
    operator fun plus(other: TreeNumberPair): TreeNumberPair {
        val pair = TreePair(this, other)
        this.parent = pair
        other.parent = pair
        return pair.reduce()
    }

    @Suppress("KotlinConstantConditions")
    fun reduce(): TreeNumberPair {
        var pairToExplode: TreePair?
        var numberToSplit: TreeNumber?
        var root = this
        do {
            pairToExplode = searchPairToExplode(root, 0)
            if (pairToExplode != null) {
                val parent = pairToExplode.parent!! as TreePair
                val exploded = pairToExplode.explode()
                exploded.parent = pairToExplode.parent
                if (pairToExplode == parent.left) parent.left = exploded
                if (pairToExplode == parent.right) parent.right = exploded
                numberToSplit = null
            }
            else {
                numberToSplit = searchNumberToSplit(root)
                if (numberToSplit != null) {
                    val splitted = numberToSplit.split()
                    splitted.parent = numberToSplit.parent
                    if (numberToSplit.parent != null) {
                        val parent = numberToSplit.parent!! as TreePair
                        if (numberToSplit == parent.left) parent.left = splitted
                        if (numberToSplit == parent.right) parent.right = splitted
                    }
                    else {
                        root = splitted
                    }

                }
            }
        } while (pairToExplode != null || numberToSplit != null)
        return root
    }

    fun split(): TreeNumberPair {
        if (this !is TreeNumber) throw IllegalArgumentException("Only numbers can be split")
        if (value < 10) throw IllegalArgumentException("Split is only for values >= 10: actual value = $value")
        val left = TreeNumber(floor(value.toDouble() / 2).toInt())
        val right = TreeNumber(ceil(value.toDouble() / 2).toInt())
        val pair = TreePair(left, right)
        left.parent = pair
        right.parent = pair
        return pair
    }

    fun explode(): TreeNumberPair {
        if (this !is TreePair) throw IllegalArgumentException("Only pairs can be exploded")
        if (this.left !is TreeNumber || this.right !is TreeNumber) throw IllegalArgumentException("Only pairs of numbers can be exploded")
        val firstLeft = searchLeft(this)
        val firstRight = searchRight(this)
        if (firstLeft != null) {
            val number = searchFirstNumber(firstLeft.left)!!
            val newNumber = TreeNumber(number.value + (this.left as TreeNumber).value)
            newNumber.parent = number.parent
            if ((number.parent as TreePair).left == number) (number.parent as TreePair).left = newNumber
            else if ((number.parent as TreePair).right == number) (number.parent as TreePair).right = newNumber
        }
        if (firstRight != null) {
            val number = searchFirstNumber(firstRight.right)!!
            val newNumber = TreeNumber(number.value + (this.right as TreeNumber).value)
            newNumber.parent = number.parent
            if ((number.parent as TreePair).left == number) (number.parent as TreePair).left = newNumber
            else if ((number.parent as TreePair).right == number) (number.parent as TreePair).right = newNumber
        }
        return TreeNumber(0)
    }

    override fun toString(): String {
        val buffer = StringBuffer()
        stringHelper(this, buffer)
        return buffer.toString()
    }

    companion object {
        private fun stringHelper(root: TreeNumberPair, buffer: StringBuffer) {
            when (root) {
                is TreeNumber -> buffer.append(root.value)
                is TreePair -> {
                    buffer.append("[")
                    stringHelper(root.left, buffer)
                    buffer.append(",")
                    stringHelper(root.right, buffer)
                    buffer.append("]")
                }
                else -> {}
            }
        }

        private fun searchPairToExplode(root: TreeNumberPair, depth: Int): TreePair? = when (root) {
            is TreeNumber -> null
            is TreePair -> {
                if (depth >= 4) root
                else searchPairToExplode(root.left, depth + 1) ?: searchPairToExplode(root.right, depth + 1)
            }
            else -> null
        }

        private fun searchNumberToSplit(root: TreeNumberPair): TreeNumber? = when (root) {
            is TreeNumber -> {
                if (root.value >= 10) root
                else null
            }
            is TreePair -> searchNumberToSplit(root.left) ?: searchNumberToSplit(root.right)
            else -> null
        }

        private fun searchFirstNumber(root: TreeNumberPair): TreeNumber? = when (root) {
            is TreeNumber -> root
            is TreePair -> searchFirstNumber(root.left) ?: searchFirstNumber(root.right)
            else -> null
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
