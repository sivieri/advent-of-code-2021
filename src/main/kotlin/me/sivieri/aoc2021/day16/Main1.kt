package me.sivieri.aoc2021.day16

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(16)
        val bitsParser = BitsParser()
        val msg = bitsParser.parseMessage(data)
        val result = msg.versionSum()
        println(result)
    }

}