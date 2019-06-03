package graphs

/**
 * Model of graph
 */
abstract class Graph<T>(private val maxVertexCount: Int) {
    protected val vertexList = mutableListOf<Vertex<T>>()
    protected val adjMatrix =
            Array(maxVertexCount) { Array(maxVertexCount) { false } }
    private var currentVertexCount = 0

    /**
     * adds vertex with certain mark to graph
     *
     * @param value - value (any possible object type) of vertex which will be created
     * @return true if vertex was created and false
     * if the number of vertices for this graph has been exceeded
     * or vertex with this mark already is in list
     */
    fun addVertex(value: T): Boolean {
        val vertexIsAlreadyIn = vertexList
                .map { it.value }
                .contains(value)

        return if (vertexList.count() < maxVertexCount && !vertexIsAlreadyIn) {
            currentVertexCount++
            vertexList.add(Vertex(value))
        } else {
            false
        }
    }

    /**
     * adds edge between two vertex
     *
     * @param first - value of first vertex in vertexList
     * @param second - value of second vertex in vertexList
     * @return true if edge was successfully added and false if vertex with such value doesn't exist
     */
    abstract fun addEdge(first: T, second: T): Boolean

    override fun toString() = vertexList.joinToString(",") { it.value.toString() }

    /**
     * inner class graph's vertex
     * container for any object type (value)
     */
    protected inner class Vertex<T>(val value: T, var wasVisited: Boolean = false) {
        override fun toString() = value.toString()
    }
}