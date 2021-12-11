package me.sivieri.aoc2021.day11

import me.sivieri.aoc2021.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(11)
        val model = OctopusesModel(data)
        val result = model.findSimultaneousStep()
        println(result)
    }

}