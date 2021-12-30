package me.sivieri.aoc2021.day20

class ImageEnhancer(input: String) {

    private val algorithm: Array<Char>
    private val startingImage: Image

    init {
        val (algorithmString, imageString) = input.split("\n\n", limit = 2)
        algorithm = algorithmString.toList().toTypedArray()
        startingImage = Image.fromString(imageString)
    }

    fun enhance(iterations: Int): Image {
        val enlarged = startingImage.enlarge(iterations * 10)
        val enhanced = (1..iterations).fold(enlarged) { acc, i ->
            println("Iteration $i")
            acc.enhance(algorithm)
        }
        return enhanced.cut((iterations - 1) * 10)
    }

}