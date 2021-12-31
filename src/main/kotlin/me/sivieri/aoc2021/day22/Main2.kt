package me.sivieri.aoc2021.day22

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(22)
        val reactor = Reactor()
        reactor.reboot(data)
        val result = reactor.countActiveCubes()
        println(result)
    }

}