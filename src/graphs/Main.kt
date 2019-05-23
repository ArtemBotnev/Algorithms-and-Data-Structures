package graphs

fun main() {
    val graph = Graph<Char>(5).apply {
        addVertex('A')
        addVertex('B')
        addVertex('C')
        addVertex('D')
        addVertex('E')
        addVertex('C')
        addVertex('F')
        addVertex('G')
    }.apply {
        addEdge('A', 'B')
        addEdge('A', 'C')
        addEdge('B', 'D')
        addEdge('D', 'E')
    }

    println(graph)
    println()

    println("Depth First Search")
    graph.dfs { print("visited $it; ") }
}