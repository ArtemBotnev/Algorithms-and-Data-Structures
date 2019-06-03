package graphs

class DirectedGraph<T>(maxVertexCount: Int) : Graph<T>(maxVertexCount) {

    override fun addEdge(first: T, second: T): Boolean {
        val values = vertexList.map { it.value }
        val start = values.indexOf(first)
        val end = values.indexOf(second)

        if (start < 0 || end < 0) return false

        adjMatrix[start][end] = true

        return true
    }
}