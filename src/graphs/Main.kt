package graphs

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

    println("Graph with vertices: $graph")

    println("Depth First Search")
    graph.dfs { print("visited $it ") }

    println()

    println("Breadth First Search")
    graph.bfs { print("visited $it ") }
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

    println()
    println("Graph with vertices: $graph")

    println("Minimum Spanning Tree")
    graph.mst { first, second -> print("$first-$second ") }
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

    println()
    println("Graph with vertices: $graph")

    println("Topological sorting")
    graph.ts { print("vertex $it ") }

    // make graph cyclical and get exception
    println()
    println()
    println("Attempt sorting cyclical graph")
    graph.addEdge('E', 'A')
    graph.ts { print("vertex $it ") }
}