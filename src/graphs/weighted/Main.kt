package graphs.weighted

import utils.Timer

private const val GRAPH_MAX_SIZE = 10
private const val INFINITY = 10_000L

fun main() {
    undirectedWeightedMST()
    directedWeightedShortest()
}

private fun undirectedWeightedMST() {
    val graph = UndirectedWeightedGraph<Char>(GRAPH_MAX_SIZE, INFINITY).apply {
        addVertex('A')
        addVertex('B')
        addVertex('C')
        addVertex('D')
        addVertex('E')
        addVertex('F')
        addVertex('J')

        addEdge('A', 'B', 2)
        addEdge('A', 'C', 8)
        addEdge('B', 'D', 5)
        addEdge('C', 'E', 9)
        addEdge('C', 'E', 9)
        addEdge('B', 'J', 10_001)
        addEdge('D', 'J', 4)
        addEdge('C', 'J', 1)
        addEdge('E', 'J', 7)
        addEdge('D', 'E', 1)
        addEdge('D', 'F', 5)
        addEdge('C', 'F', 6)
    }

    println()
    println("Graph with vertices: $graph")
    println("Minimum Spanning Tree of weighted graph")
    val timer = Timer().apply { start() }
    graph.mstw {from, to, weight ->
        println("$from-($weight)-$to")
    }
    println(timer.stopAndShowTime())
    println()
}

private fun directedWeightedShortest() {
    val vertices = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'J')
    val graph = DirectedWeightedGraph<Char>(GRAPH_MAX_SIZE, INFINITY).apply {
        vertices.forEach { addVertex(it) }

        addEdge('A', 'B', 8)
        addEdge('A', 'J', 1)
        addEdge('B', 'E', 5)
        addEdge('C', 'A', 3)
        addEdge('C', 'J', 10)
        addEdge('D', 'B', 4)
        addEdge('E', 'C', 3)
        addEdge('E', 'F', 6)
        addEdge('F', 'A', 7)
        addEdge('F', 'D', 3)
        addEdge('J', 'D', 2)
    }

    println("Directed weighted graph with vertices: $graph")
    println("Shortest paths:")
    println()

    val timer = Timer()
    vertices.forEach { findShortestPathsFrom(graph, it, timer) }
}

private fun <T> findShortestPathsFrom(graph: DirectedWeightedGraph<T>, vertex: T, timer: Timer) {
    println("shortest paths from vertex $vertex:")
    timer.start()

    val paths = graph.shortestPath(vertex)
    paths?.run {
        forEach { pair ->
            println("path: ${pair.first.joinToString("->")} sum: ${pair.second}")
        }
    } ?: println("There are some unreachable vertices in the graph")

    println(timer.stopAndShowTime())
    println()
}
