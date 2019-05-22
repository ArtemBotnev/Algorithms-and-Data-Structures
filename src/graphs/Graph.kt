package graphs

/**
 * Model of graph
 */
class Graph(private val maxVertexCount: Int) {

    private val vertexList = mutableListOf<Vertex>()
    private val adjMatrix =
            Array(maxVertexCount) { Array(maxVertexCount) { false } }
    private var currentVertexCount = 0

    /**
     * adds vertex with certain mark to graph
     *
     * @param mark - mark of vertex which will be created
     * @return true if vertex was created and false
     * if the number of vertices for this graph has been exceeded
     * or vertex with this mark already is in list
     */
    fun addVertex(mark: Char): Boolean {
        val vertexIsAlreadyIn = vertexList
                .map { it.mark }
                .contains(mark)

        return if (vertexList.count() < maxVertexCount && !vertexIsAlreadyIn) {
            currentVertexCount++
            vertexList.add(Vertex(mark))
        } else {
            false
        }
    }


    /**
     * adds edge between two vertex
     *
     * @param first - mark of first vertex in vertexList
     * @param second - mark of second vertex in vertexList
     * @return true if edge was successfully added and false if vertex with such mark doesn't exist
     */
    fun addEdge(first: Char, second: Char): Boolean {
        val marks = vertexList.map { it.mark }
        val start = marks.indexOf(first)
        val end = marks.indexOf(second)

        if (start < 0 || end < 0) return false

        adjMatrix[start][end] = true
        adjMatrix[end][start] = true

        return true
    }

    override fun toString() = vertexList.joinToString(",") { it.mark.toString() }

    /**
     * inner class graph's vertex
     * char used as a mark of vertex
     */
    private inner class Vertex(val mark: Char, var wasVisited: Boolean = false)
}