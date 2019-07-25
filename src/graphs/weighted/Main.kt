package graphs.weighted

import utils.Timer

private const val GRAPH_MAX_SIZE = 10
private const val INFINITY = 10_000L

fun main() {
    minimumSpanningTree()
}

private fun minimumSpanningTree() {
//    undirected()
    val timer = Timer().apply { start() }
    directed()
    println(timer.stopAndShowTime())
}

private fun undirected() {
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

    val timer = Timer().apply { start() }
    graph.mstw {from, to, weight ->
        println("$from-($weight)-$to")
    }
    println(timer.stopAndShowTime())
}

private fun directed() {
    val graph = DirectedWeightedGraph<Char>(GRAPH_MAX_SIZE, INFINITY).apply {
        addVertex('A')
        addVertex('B')
        addVertex('C')
        addVertex('D')
        addVertex('E')
        addVertex('F')
        addVertex('J')

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

//    graph.shortestPath('A') { println(it) }
//    graph.shortestPath('B') { println(it) }
//    graph.shortestPath('C') { println(it) }
    graph.shortestPath('D') { println(it) }
}