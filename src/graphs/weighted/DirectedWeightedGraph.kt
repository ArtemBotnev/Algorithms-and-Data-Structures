package graphs.weighted

class DirectedWeightedGraph<T>(maxVertexCount: Int, infinity: Long)
    : WeightedGraph<T>(maxVertexCount, infinity) {

    override fun addEdge(first: T, second: T, weight: Long): Boolean {
        val values = vertexList.map { it.value }
        val start = values.indexOf(first)
        val end = values.indexOf(second)

        if (start < 0 || end < 0) return false

        adjMatrix[start][end] = weight

        return true
    }

    @Throws(NotSuchVertexException::class)
    fun shortestPath(from: T, action: (result: String) -> Unit) {
        val pathList = mutableListOf<Path<T>>()
        val startVertexIndex = vertexList.map { it.value }.indexOf(from)
        var visitedVerticesCount = 1
        var startToCurrent = 0L

        if (startVertexIndex < 0) throw NotSuchVertexException()

        var currentVertex = vertexList[startVertexIndex].also { it.wasVisited = true }

        for (i in 0 until currentVertexCount) {
            val weight = adjMatrix[startVertexIndex][i]
            // add new edge from start vertex to list
            pathList.add(Path(currentVertex, weight))
        }

        // till all vertices will be visited
        while (visitedVerticesCount < currentVertexCount) {
            val shortestPathIndex = getMinWeightVertexIndex(startVertexIndex, pathList)
            val shortestPath = pathList[shortestPathIndex].weight

            if (shortestPath >= infinity) {
                action("There are some unreachable vertices in the graph")
                break
            } else {
                currentVertex = vertexList[shortestPathIndex]
                action(shortestPath.toString())
            }

            currentVertex.wasVisited = true
            visitedVerticesCount++
            adjustPaths(pathList, currentVertex, startToCurrent)
        }
    }

    private fun getMinWeightVertexIndex(
            startVertexIndex: Int, paths: MutableList<Path<T>>): Int {
        var minWeight = infinity
        var minIndex = 0
        for (i in 0 until currentVertexCount) {
            // skip start vertex
            if (startVertexIndex == i) continue

            if (!vertexList[i].wasVisited && paths[i].weight < minWeight) {
                minWeight = paths[i].weight
                minIndex = i
            }
        }

        return minIndex
    }

    private fun adjustPaths(
            paths: MutableList<Path<T>>,
            currentVertex: Vertex<T>,
            startToCurrent: Long) {

        val currentIndex = vertexList.indexOf(currentVertex)

        for (i in 0 until currentVertexCount) {
            if (i == currentIndex) continue
            if (vertexList[i].wasVisited) continue

            val currentToDist = adjMatrix[currentIndex][i]
            val startToDist = startToCurrent + currentToDist
            val path = paths[i].weight

            if (startToDist < path) {
                paths[i].run {
                    updateCurrentVertex(vertexList[currentIndex])
                    weight = startToDist
                }
            }
        }
    }

    private class Path<T>(startVertex: Vertex<T>, var weight: Long) {
        private val pathPoints = mutableListOf<Vertex<T>>()
        private var currentVertex: Vertex<T>

        init {
            currentVertex = startVertex
        }

        fun updateCurrentVertex(vertex: Vertex<T>) {
            pathPoints.add(currentVertex)
            currentVertex = vertex
        }
    }
}