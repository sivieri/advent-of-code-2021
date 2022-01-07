package me.sivieri.aoc2021.day24

import java.lang.IllegalArgumentException

class MonadInterpreter(private val instructions: List<String>) {

    private val state: MutableMap<String, Int> = mutableMapOf()

    fun run(input: Long): Map<String, Int> {
        val data = input.toString().toList().map { it.toString().toInt() }
        var i = 0
        state["w"] = 0
        state["x"] = 0
        state["y"] = 0
        state["z"] = 0
        instructions
            .filterNot { it.isBlank() }
            .forEach { instruction ->
                val parts = instruction.split(" ")
                when (parts[0]) {
                    "inp" -> state[parts[1]] = data[i++]
                    "add" -> {
                        if (parts[2].toList()[0] in 'a'..'z') state[parts[1]] = state[parts[1]]!! + state[parts[2]]!!
                        else state[parts[1]] = state[parts[1]]!! + parts[2].toInt()
                    }
                    "mul" -> {
                        if (parts[2].toList()[0] in 'a'..'z') state[parts[1]] = state[parts[1]]!! * state[parts[2]]!!
                        else state[parts[1]] = state[parts[1]]!! * parts[2].toInt()
                    }
                    "div" -> {
                        if (parts[2].toList()[0] in 'a'..'z') state[parts[1]] = state[parts[1]]!! / state[parts[2]]!!
                        else state[parts[1]] = state[parts[1]]!! / parts[2].toInt()
                    }
                    "mod" -> {
                        if (parts[2].toList()[0] in 'a'..'z') state[parts[1]] = state[parts[1]]!! % state[parts[2]]!!
                        else state[parts[1]] = state[parts[1]]!! % parts[2].toInt()
                    }
                    "eql" -> {
                        val other = if (parts[2].toList()[0] in 'a'..'z') state[parts[2]]!!
                        else parts[2].toInt()
                        if (state[parts[1]] == other) state[parts[1]] = 1
                        else state[parts[1]] = 0
                    }
                    else -> throw IllegalArgumentException("Unsupported operation $instruction")
                }
            }
        return state.toMap()
    }

}