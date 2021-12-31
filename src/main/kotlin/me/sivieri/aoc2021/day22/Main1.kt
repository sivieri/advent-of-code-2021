package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(22)
        val reactor = Reactor()
        reactor.reboot(data, limit = 50)
        val result = reactor.countActiveCubes()
        println(result)
    }

}