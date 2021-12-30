package me.sivieri.aoc2021.day20

data class Image(
    val image: Array<Array<Char>>,
    val x: Int = image[0].size,
    val y: Int = image.size
) {

    fun enhance(algorithm: Array<Char>): Image {
        val data = (0 until x).map { x ->
            (0 until y).map { y ->
                findPosition(y, x)
            }.toTypedArray()
        }.toTypedArray()
        val output = data.map { line ->
            line.map { algorithm[it] }.toTypedArray()
        }
            .toTypedArray()
        return Image(output)
    }

    private fun findPosition(x: Int, y: Int): Int {
        val data = listOf(
            getPixel(y - 1, x - 1),
            getPixel(y - 1, x),
            getPixel(y - 1, x + 1),
            getPixel(y, x - 1),
            getPixel(y, x),
            getPixel(y, x + 1),
            getPixel(y + 1, x - 1),
            getPixel(y + 1, x),
            getPixel(y + 1, x + 1)
        )
        val s = data
            .map { c -> if (c == DARK_PIXEL) '0' else '1' }
            .joinToString("")
        return Integer.parseInt(s, 2)
    }

    private fun getPixel(y: Int, x: Int): Char =
        if (y < 0 || x < 0 || y >= this.y || x >= this.x) DARK_PIXEL
        else image[y][x]

    fun enlarge(size: Int): Image = Image(
        Array(size) { Array(x + size * 2) { DARK_PIXEL } } +
        image.map { (Array(size) { DARK_PIXEL } + it + Array(size) { DARK_PIXEL }) }.toTypedArray() +
        Array(size) { Array(x + size * 2) { DARK_PIXEL } }
    )

    fun cut(size: Int): Image = Image(
        image
            .toList()
            .subList(size, image.size - size)
            .map { line ->
                line
                    .toList()
                    .subList(size, line.size - size)
                    .toTypedArray()
            }
            .toTypedArray()
    )

    fun countLight(): Int =
        image.sumOf { it.count { it == LIGHT_PIXEL } }

    fun stringRepresentation(): String =
        image.joinToString("\n") { line ->
            line.joinToString("")
        }

    override fun toString(): String {
        return "Image(x=$x,y=$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (!image.contentDeepEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        return image.contentDeepHashCode()
    }

    companion object {
        private const val LIGHT_PIXEL = '#'
        private const val DARK_PIXEL = '.'

        fun fromString(input: String): Image {
            val data = input
                .split("\n")
                .filter { it.isNotBlank() }
                .map { line ->
                    line.toList().toTypedArray()
                }
                .toTypedArray()
            return Image(data)
        }
    }

}