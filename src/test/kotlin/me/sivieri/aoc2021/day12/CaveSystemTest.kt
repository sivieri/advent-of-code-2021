package me.sivieri.aoc2021.day12

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CaveSystemTest {

    @Test
    fun `all paths small example`() {
        val input = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().split("\n")
        val caveSystem = CaveSystem(input)
        val paths = caveSystem.findAllPaths()
        assertThat(paths.size, `is`(10))
    }

    @Test
    fun `all paths medium example`() {
        val input = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent().split("\n")
        val caveSystem = CaveSystem(input)
        val paths = caveSystem.findAllPaths()
        assertThat(paths.size, `is`(19))
    }

    @Test
    fun `all paths large example`() {
        val input = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent().split("\n")
        val caveSystem = CaveSystem(input)
        val paths = caveSystem.findAllPaths()
        assertThat(paths.size, `is`(226))
    }

}