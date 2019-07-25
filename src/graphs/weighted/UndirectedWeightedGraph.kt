package graphs.weighted

import java.util.*
import kotlin.collections.HashMap

/**
 * Model of undirected weighted graph
 */
class UndirectedWeightedGraph<T>(maxVertexCount: Int, infinity: Long)
    : WeightedGraph<T>(maxVertexCount, infinity) {

    override fun addEdge(first: T, second: T, weight: Long): Boolean {
        val values = vertexList.map { it.value }
        val start = values.indexOf(first)
        val end = values.indexOf(second)

        if (start < 0 || end < 0) return false

        adjMatrix[start][end] = weight
        adjMatrix[end][start] = weight

        return true
    }

    /**
     * Minimum Spanning Tree of Weighted graph function
     * makes vertices connected with minimum edges count = vertices - 1
     *
     * @param action - lambda what perform an action with two bound vertices values
     * and weight between them
     */
    fun mstw(action: (T?, T?, Long) -> Unit) {
        // priority queue of graph vertices
        val pQueue = PriorityQueue<Edge<T>>(currentVertexCount)
        // map - contains info if particular vertex has already bound in MST or not
        val wasBoundMap = HashMap<Vertex<T>, Boolean>(currentVertexCount)
        // count of vertices added in MST
        var addedVerticesCount = 0
        var currentVertex: Vertex<T> = vertexList[0]

        // fill connectivity map
        vertexList.forEach { wasBoundMap[it] = false }

        while (addedVerticesCount < currentVertexCount - 1) {
            currentVertex.wasVisited = true
            addedVerticesCount++

            val currentVertexIndex = vertexList.indexOf(currentVertex)
            for (i in 0 until currentVertexCount) {
                if (i == currentVertexIndex) continue
                if (vertexList[i].wasVisited) continue

                val weight = adjMatrix[currentVertexIndex][i]
                if (weight >= infinity) continue

                val destinationVertex = vertexList[i]
                // create edge and put it in queue
                pQueue.add(Edge(currentVertex, destinationVertex, weight))
            }

            if (pQueue.isEmpty()) {
                // graph isn't connected
                action(null, null, infinity)
                return
            }

            var minWeightEdge: Edge<T>
            do {
                // get edge with min weight
                minWeightEdge = pQueue.remove()
                // check if this vertex has already bound and skip it
            } while (wasBoundMap[minWeightEdge.to]!!)
            // add to map
            wasBoundMap[minWeightEdge.to] = true

            val vertexFrom = minWeightEdge.from
            currentVertex = minWeightEdge.to

            action(vertexFrom.value, currentVertex.value, minWeightEdge.weight)
        }

        // flags reset
        vertexList.forEach { it.wasVisited = false }
    }

    /**
     * Represents edge between two vertex and weight of this edge
     */
    private class Edge<T>(val from: Vertex<T>, val to: Vertex<T>, val weight: Long)
        : Comparable<Edge<T>> {
        override fun compareTo(other: Edge<T>) = this.weight.compareTo(other.weight)
    }
}