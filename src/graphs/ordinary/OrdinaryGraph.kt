package graphs.ordinary

import graphs.Graph

/**
 * Model of not weighted graph
 */
abstract class OrdinaryGraph<T>(maxVertexCount: Int) : Graph<T>(maxVertexCount) {

    protected val adjMatrix =
            Array(maxVertexCount) { Array(maxVertexCount) { false } }

    /**
     * adds edge between two vertex
     *
     * @param first - value of first vertex in vertexList
     * @param second - value of second vertex in vertexList
     * @return true if edge was successfully added and false if vertex with such value doesn't exist
     */
    abstract fun addEdge(first: T, second: T): Boolean
}