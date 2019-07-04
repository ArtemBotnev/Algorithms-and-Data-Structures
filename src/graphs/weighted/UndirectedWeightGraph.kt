package graphs.weighted

import java.util.*
import kotlin.collections.HashMap

class UndirectedWeightGraph<T>(maxVertexCount: Int, infinity: Long)
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

    fun mstw(action: (T?, T?, Long) -> Unit) {
        val pQueue = PriorityQueue<Edge<T>>(currentVertexCount)
        val wasBoundMap = HashMap<Vertex<T>, Boolean>(currentVertexCount)
        var addedVerticesCount = 0
        var currentVertex: Vertex<T> = vertexList[0]
        wasBoundMap[currentVertex] = false

        // fill visit map
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
                pQueue.add(Edge(currentVertex, destinationVertex, weight))
            }

            if (pQueue.isEmpty()) {
                // graph isn't connected
                action(null, null, infinity)
                return
            }

            var minWeightEdge: Edge<T>
            do {
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
}