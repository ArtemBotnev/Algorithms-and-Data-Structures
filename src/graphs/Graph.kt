package graphs

/**
 * Model of graph
 */
abstract class Graph<T>(private val maxVertexCount: Int) {
    protected val vertexList = mutableListOf<Vertex<T>>()
    protected var currentVertexCount = 0

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

    override fun toString() = vertexList.joinToString(",") { it.value.toString() }

    /**
     * class graph's vertex
     * container for any object type (value)
     */
    class Vertex<T>(val value: T, var wasVisited: Boolean = false) {
        override fun toString() = value.toString()
    }
}