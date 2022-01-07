package me.sivieri.aoc2021.day24

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(24)
        val tree = MonadTree(data)
        val result = tree.searchMin()
        println(result)
    }

}