package me.sivieri.aoc2021.day24

class MonadTree(input: List<String>) {

    private val instructions: List<String> = input.filterNot { it.isBlank() }
    private var states = mapOf<MonadState, Long>(
        MonadState(0, 0, 0, 0) to 0
    )

    fun searchMax(): Long {
        instructions.forEachIndexed { index, instruction ->
            println("Instruction ${index + 1}, states ${states.size}")
            val parts = instruction.split(" ")
            states = if (parts[0] == "inp") {
                val a = parts[1].toCharArray()[0]
                states
                    .flatMap { state ->
                        (1..9).map { i ->
                            val newKey = assign(a, i, state.key)
                            val newValue = (state.value.toString() + i.toString()).toLong()
                            newKey to newValue
                        }
                    }
                    .groupBy(keySelector = { it.first }, valueTransform = { it.second })
                    .map { it.key to it.value.maxOrNull()!! }
                    .toMap()
            }
            else {
                states
                    .map { (state, value) ->
                        applyInstruction(state, parts[0], parts[1].toCharArray()[0], parts[2]) to value
                    }
                    .groupBy(keySelector = { it.first }, valueTransform = { it.second })
                    .map { it.key to it.value.maxOrNull()!! }
                    .toMap()
            }
        }
        return states.filter { it.key.z == 0 }.maxOf { it.value }
    }

    fun searchMin(): Long {
        instructions.forEachIndexed { index, instruction ->
            println("Instruction ${index + 1}, states ${states.size}")
            val parts = instruction.split(" ")
            states = if (parts[0] == "inp") {
                val a = parts[1].toCharArray()[0]
                states
                    .flatMap { state ->
                        (1..9).map { i ->
                            val newKey = assign(a, i, state.key)
                            val newValue = (state.value.toString() + i.toString()).toLong()
                            newKey to newValue
                        }
                    }
                    .groupBy(keySelector = { it.first }, valueTransform = { it.second })
                    .map { it.key to it.value.minOrNull()!! }
                    .toMap()
            }
            else {
                states
                    .map { (state, value) ->
                        applyInstruction(state, parts[0], parts[1].toCharArray()[0], parts[2]) to value
                    }
                    .groupBy(keySelector = { it.first }, valueTransform = { it.second })
                    .map { it.key to it.value.minOrNull()!! }
                    .toMap()
            }
        }
        return states.filter { it.key.z == 0 }.minOf { it.value }
    }

    private fun applyInstruction(state: MonadState, op: String, a: Char, b: String): MonadState {
        val other = if (b.toCharArray()[0] in listOf('w', 'x', 'y', 'z')) getNumber(b.toCharArray()[0], state)
        else b.toInt()
        val current = getNumber(a, state)
        return when (op) {
            "add" -> assign(a, current + other, state)
            "mul" -> assign(a, current * other, state)
            "div" -> assign(a, current / other, state)
            "mod" -> assign(a, current % other, state)
            "eql" -> {
                val value = if (current == other) 1 else 0
                assign(a, value, state)
            }
            else -> throw IllegalArgumentException("Unsupported operation $op")
        }
    }

    private fun getNumber(c: Char, state: MonadState): Int = when (c) {
        'w' -> state.w
        'x' -> state.x
        'y' -> state.y
        'z' -> state.z
        else -> throw IllegalArgumentException("$c does not exist")
    }

    private fun assign(c: Char, value: Int, state: MonadState): MonadState = when (c) {
        'w' -> state.copy(w = value)
        'x' -> state.copy(x = value)
        'y' -> state.copy(y = value)
        'z' -> state.copy(z = value)
        else -> throw IllegalArgumentException("$c does not exist")
    }

}