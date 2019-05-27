package graphs

private const val GRAPH_MAX_SIZE = 8

fun main() {
    val graph = Graph<Char>(GRAPH_MAX_SIZE).apply {
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
        addEdge('A', 'B')
        addEdge('A', 'C')
        addEdge('A', 'H')
        addEdge('B', 'D')
        addEdge('D', 'E')
        addEdge('D', 'F')
        addEdge('C', 'G')
    }

    println(graph)
    println()

    println("Depth First Search")
    graph.dfs { print("visited $it ") }

    println()

    println("Breadth First Search")
    graph.bfs { print("visited $it ") }
}