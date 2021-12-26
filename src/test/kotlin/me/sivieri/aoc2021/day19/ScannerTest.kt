package me.sivieri.aoc2021.day19

import me.sivieri.aoc2021.common.Coordinate3D
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class ScannerTest {

    @Test
    fun `parsing test`() {
        val input = """
            --- scanner 0 ---
            -1,-1,1
            -2,-2,2
            -3,-3,3
            -2,-3,1
            5,6,-4
            8,0,7
        """.trimIndent()
        val scanner = Scanner.fromString(input)
        val expected = Scanner(
            0,
            listOf(
                Coordinate3D(-1, -1, 1),
                Coordinate3D(-2, -2, 2),
                Coordinate3D(-3, -3, 3),
                Coordinate3D(-2, -3, 1),
                Coordinate3D(5, 6, -4),
                Coordinate3D(8, 0, 7)
            )
        )
        assertThat(scanner, `is`(expected))
    }

    @Test
    fun `generate all combinations`() {
        val input = """
            --- scanner 0 ---
            -1,-1,1
            -2,-2,2
            -3,-3,3
            -2,-3,1
            5,6,-4
            8,0,7
        """.trimIndent()
        val scanner = Scanner.fromString(input)
        val combinations = scanner.generateAllCombinations()
        assertThat(combinations.size, `is`(48))
    }

    @Test
    fun `existing comparison between scanners`() {
        val scanner0 = Scanner.fromString("""
            --- scanner 0 ---
            404,-588,-901
            528,-643,409
            -838,591,734
            390,-675,-793
            -537,-823,-458
            -485,-357,347
            -345,-311,381
            -661,-816,-575
            -876,649,763
            -618,-824,-621
            553,345,-567
            474,580,667
            -447,-329,318
            -584,868,-557
            544,-627,-890
            564,392,-477
            455,729,728
            -892,524,684
            -689,845,-530
            423,-701,434
            7,-33,-71
            630,319,-379
            443,580,662
            -789,900,-551
            459,-707,401
        """.trimIndent())
        val scanner1 = Scanner.fromString("""
            --- scanner 1 ---
            686,422,578
            605,423,415
            515,917,-361
            -336,658,858
            95,138,22
            -476,619,847
            -340,-569,-846
            567,-361,727
            -460,603,-452
            669,-402,600
            729,430,532
            -500,-761,534
            -322,571,750
            -466,-666,-811
            -429,-592,574
            -355,545,-477
            703,-491,-529
            -328,-685,520
            413,935,-424
            -391,539,-444
            586,-435,557
            -364,-763,-893
            807,-499,-711
            755,-354,-619
            553,889,-390
        """.trimIndent())
        val result = scanner0.compareWithScanner(scanner1)
        assertThat(result?.second, `is`(notNullValue()))
    }

}