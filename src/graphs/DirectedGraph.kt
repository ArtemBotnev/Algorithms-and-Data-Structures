package graphs

/**
 * Model of directed graph
 */
class DirectedGraph<T>(maxVertexCount: Int) : Graph<T>(maxVertexCount) {

    override fun addEdge(first: T, second: T): Boolean {
        val values = vertexList.map { it.value }
        val start = values.indexOf(first)
        val end = values.indexOf(second)

        if (start < 0 || end < 0) return false

        adjMatrix[start][end] = true

        return true
    }

    /**
     * Topological sorting function
     *
     * @param action - lambda what perform an action with vertex value
     */
    fun ts(action: (T) -> Unit) {
        val sortedList = mutableListOf<Vertex<T>>()
        // copy object data to local variables not to affect them
        val vertices = vertexList.toMutableList()
        var verticesCount = vertices.count()

        val adjMatrixList: MutableList<MutableList<Boolean>> = adjMatrix.map {
            it.take(verticesCount).toMutableList()
        }.take(verticesCount).toMutableList()


        while (verticesCount > 0) {
            val currentVertex = getVertexHasNoSuccessors(adjMatrixList, vertices)
            sortedList.add(currentVertex)
            removeVertex(currentVertex, adjMatrixList, vertices)
            verticesCount--
        }

        //apply action for sorted vertices list
        sortedList.apply {
            reverse()
            forEach { action(it.value) }
        }
    }

    /**
     * find vertex which has no successors
     *
     * @return vertex without successors
     */
    @Throws(CyclicalGraphException::class)
    private fun getVertexHasNoSuccessors(
            matrix: List<List<Boolean>>, vertices: List<Vertex<T>>
    ): Vertex<T> {
        var isEdge: Boolean
        val count = vertices.count()

        for (row in 0 until count) {
            isEdge = false
            for (col in 0 until count) {
                if (matrix[row][col]) {
                    isEdge = true
                    break
                }
            }

            if (!isEdge) return vertices[row]
        }

        throw CyclicalGraphException()
    }

    private fun removeVertex(
            vertex: Vertex<T>,
            matrix: MutableList<MutableList<Boolean>>,
            vertices: MutableList<Vertex<T>>) {

        val index = vertices.indexOf(vertex)
        vertices.remove(vertex)
        // remove vertex from matrix
        matrix.run {
            forEach { it.removeAt(index) }
            removeAt(index)
        }
    }
}