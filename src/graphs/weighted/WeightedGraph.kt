package graphs.weighted

import graphs.Graph

/**
 * Model of weighted graph
 */
abstract class WeightedGraph<T>(maxVertexCount: Int, protected val infinity: Long)
    : Graph<T>(maxVertexCount) {

    // infinity - the limit value of the edge weight at which
    // the vertices are considered disconnected
    protected val adjMatrix =
            Array(maxVertexCount) { Array(maxVertexCount) { infinity } }

    /**
     * adds edge between two vertex
     *
     * @param first - value of first vertex in vertexList
     * @param second - value of second vertex in vertexList
     * @param weight - weight of this edge
     * @return true if edge was successfully added and false if vertex with such value doesn't exist
     */
    abstract fun addEdge(first: T, second: T, weight: Long): Boolean

    protected class Edge<T>(val from: Vertex<T>, val to: Vertex<T>, val weight: Long)
        : Comparable<Edge<T>> {
        override fun compareTo(other: Edge<T>) = this.weight.compareTo(other.weight)
    }
}