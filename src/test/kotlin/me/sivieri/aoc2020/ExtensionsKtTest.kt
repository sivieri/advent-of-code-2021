package me.sivieri.aoc2020

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ExtensionsKtTest {

    @Test
    fun `01 array shift positive test`() {
        val a = arrayOf(9, 3, 6, 7, 2, 5, 8, 4, 1)
        val b = Array(a.size) { 0 }
        a.shift(b, 3)
        val exp = arrayOf(8, 4, 1, 9, 3, 6, 7, 2, 5)
        Assert.assertArrayEquals(exp, b)
    }

    @Test
    fun `02 array shift negative test`() {
        val a = arrayOf(9, 3, 6, 7, 2, 5, 8, 4, 1)
        val b = Array(a.size) { 0 }
        a.shift(b, -3)
        val exp = arrayOf(7, 2, 5, 8, 4, 1, 9, 3, 6)
        Assert.assertArrayEquals(exp, b)
    }

    @Test
    fun `03 array shift max negative test`() {
        val a = arrayOf(7, 4, 1, 9, 5, 8, 3, 2, 6)
        val b = Array(a.size) { 0 }
        a.shift(b, -8)
        val exp = arrayOf(6, 7, 4, 1, 9, 5, 8, 3, 2)
        Assert.assertArrayEquals(exp, b)
    }

}