package graphs.ordinary

import utils.Timer

private const val GRAPH_MAX_SIZE = 8

fun main() {
    firstSearchAlgorithms()
    println()
    minimumSpanningTree()
    println()
    topologicalSorting()
}

private fun firstSearchAlgorithms() {
    // create and fill graph
    val graph = UndirectedGraph<Char>(GRAPH_MAX_SIZE).apply {
        addVertex('A')
        addVertex('B')
        addVertex('C')
        addVertex('D')
        addVertex('E')
        addVertex('C')
        addVertex('F')
        addVertex('G')
        addVertex('H')
        addVertex('I')
        addVertex('J')
    }.apply {
        // bind graph vertices
        addEdge('A', 'B')
        addEdge('A', 'C')
        addEdge('A', 'H')
        addEdge('B', 'D')
        addEdge('D', 'E')
        addEdge('D', 'F')
        addEdge('C', 'G')
    }

    val timer = Timer()
    println("Graph with vertices: $graph")

    println("Depth First Search")
    timer.start()
    graph.dfs { print("visited $it ") }
    println()
    println(timer.stopAndShowTime())

    println()

    println("Breadth First Search")
    timer.start()
    graph.bfs { print("visited $it ") }
    println()
    println(timer.stopAndShowTime())
}

private fun minimumSpanningTree() {
    val graph = UndirectedGraph<Char>(GRAPH_MAX_SIZE).apply {
        addVertex('A')
        addVertex('B')
        addVertex('C')
        addVertex('D')
        addVertex('E')
        addVertex('F')
    }.apply {
        // bind graph vertices
        addEdge('A', 'B')
        addEdge('A', 'C')
        addEdge('B', 'D')
        addEdge('B', 'E')
        addEdge('C', 'E')
        addEdge('D', 'E')
        addEdge('D', 'F')
        addEdge('E', 'F')
    }

    val timer = Timer()
    println()
    println("Graph with vertices: $graph")

    println("Minimum Spanning Tree")
    timer.start()
    graph.mst { first, second -> print("$first-$second ") }
    println()
    println(timer.stopAndShowTime())
}

private fun topologicalSorting() {
    val graph = DirectedGraph<Char>(GRAPH_MAX_SIZE).apply {
        addVertex('A')
        addVertex('B')
        addVertex('C')
        addVertex('D')
        addVertex('E')
        addVertex('F')
    }.apply {
        // bind graph vertices
        addEdge('A', 'B')
        addEdge('A', 'C')
        addEdge('B', 'D')
        addEdge('D', 'E')
        addEdge('C', 'F')
    }

    val timer = Timer()
    println()
    println("Graph with vertices: $graph")

    println("Topological sorting")
    timer.start()
    graph.ts { print("vertex $it ") }
    println()
    println(timer.stopAndShowTime())

    // make graph cyclical and get exception
    println()
    println()
    println("Attempt sorting cyclical graph")
    graph.addEdge('E', 'A')
    graph.ts { print("vertex $it ") }
}