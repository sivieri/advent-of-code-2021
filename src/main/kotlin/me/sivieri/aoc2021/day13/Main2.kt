package me.sivieri.aoc2021.day13

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(13)
        val origami = Origami(data)
        origami.instructions.forEach {
            println("${origami.image.size}, ${origami.image[0].size}")
            origami.applyFold(it)
        }
        println(origami.getBoard())
    }

}