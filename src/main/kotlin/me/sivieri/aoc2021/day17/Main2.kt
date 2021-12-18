package me.sivieri.aoc2021.day17

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val trench = Trench(240, 292, -90, -57)
        val result = trench.findAllCouplesCount()
        println(result)
    }

}