package me.sivieri.aoc2021.day23

import me.sivieri.aoc2021.crossProduct
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsInAnyOrder
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import org.junit.Test

class GraphHelperTest {

    @Test
    fun `generate the correct directed graph from an internal cell`() {
        val boardString =
            "#############\n" +
            "#.....D.D.A.#\n" +
            "###.#B#C#.###\n" +
            "  #A#B#C#.#  \n" +
            "  #########  "
        val board = BoardStateWithCost.fromString(boardString)
        val graph = GraphHelper.generateDirectedGraph(board, 6)

        val expected = SimpleDirectedGraph<BoardCell, DefaultEdge>(DefaultEdge::class.java)
        (1..19).forEach {
            expected.addVertex(BoardCell(it, board.positions[it]))
        }
        expected.addEdge(BoardCell(6, board.positions[6]), BoardCell(5, board.positions[5]))
        expected.addEdge(BoardCell(5, board.positions[5]), BoardCell(4, board.positions[4]))
        expected.addEdge(BoardCell(4, board.positions[4]), BoardCell(3, board.positions[3]))
        expected.addEdge(BoardCell(3, board.positions[3]), BoardCell(2, board.positions[2]))
        expected.addEdge(BoardCell(2, board.positions[2]), BoardCell(1, board.positions[1]))
        expected.addEdge(BoardCell(3, board.positions[3]), BoardCell(12, board.positions[12]))
        expected.addEdge(BoardCell(12, board.positions[12]), BoardCell(16, board.positions[16]))
        expected.addEdge(BoardCell(5, board.positions[5]), BoardCell(13, board.positions[13]))
        expected.addEdge(BoardCell(13, board.positions[13]), BoardCell(17, board.positions[17]))
        expected.addEdge(BoardCell(6, board.positions[6]), BoardCell(7, board.positions[7]))
        expected.addEdge(BoardCell(7, board.positions[7]), BoardCell(14, board.positions[14]))
        expected.addEdge(BoardCell(14, board.positions[14]), BoardCell(18, board.positions[18]))
        expected.addEdge(BoardCell(7, board.positions[7]), BoardCell(8, board.positions[8]))
        expected.addEdge(BoardCell(8, board.positions[8]), BoardCell(9, board.positions[9]))
        expected.addEdge(BoardCell(9, board.positions[9]), BoardCell(15, board.positions[15]))
        expected.addEdge(BoardCell(15, board.positions[15]), BoardCell(19, board.positions[19]))
        expected.addEdge(BoardCell(9, board.positions[9]), BoardCell(10, board.positions[10]))
        expected.addEdge(BoardCell(10, board.positions[10]), BoardCell(11, board.positions[11]))

        val firstVertex = graph.vertexSet()
        val secondVertex = expected.vertexSet()
        assertThat(firstVertex, containsInAnyOrder(*secondVertex.toTypedArray()))
        val edgeCheck = firstVertex.crossProduct(secondVertex).all { pair ->
            graph.containsEdge(pair.first, pair.second) == expected.containsEdge(pair.first, pair.second)
        }
        assertThat(edgeCheck, `is`(true))
    }

}