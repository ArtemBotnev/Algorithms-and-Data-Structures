package graphs.weighted

import java.lang.StringBuilder

/**
 * Model of directed weighted graph
 */
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

    /**
     * finds shortest path from particular vertex to each vertex of this graph
     * Dijkstra’s algorithm
     *
     * @param from - the value of the vertex from which the path should begin
     * @param report - lambda that takes string report of shortest paths
     * @throws NotSuchVertexException - in case the vertex with value @from hasn't been found
     */
    @Throws(NotSuchVertexException::class)
    fun shortestPath(from: T, report: (String) -> Unit) {
        // list of all paths from start vertex
        val pathList = mutableListOf<Path<T>>()
        val startVertexIndex = vertexList.map { it.value }.indexOf(from)
        // startVertexIndex is already visited
        var visitedVerticesCount = 1

        if (startVertexIndex < 0) throw NotSuchVertexException()
        // vertex form which begin part of full path in particular moment
        var parentVertex = vertexList[startVertexIndex].also { it.wasVisited = true }

        // fill path list
        for (i in 0 until currentVertexCount) {
            val weight = adjMatrix[startVertexIndex][i]
            // add new path from start vertex to list
            pathList.add(Path(parentVertex, weight))
        }

        // till all vertices will be visited
        while (visitedVerticesCount < currentVertexCount) {
            val shortestPathIndex = getMinWeightVertexIndex(startVertexIndex, pathList)
            val shortestPath = pathList[shortestPathIndex]

            if (shortestPath.weight >= infinity) {
                report("There are unreachable vertices in the graph")
                break
            } else {
                // assign vertex that is start of shortest path as parent vertex
                parentVertex = vertexList[shortestPathIndex]
            }

            parentVertex.wasVisited = true
            visitedVerticesCount++
            adjustPaths(pathList, parentVertex, shortestPath)
        }

        // flags reset
        vertexList.forEach { it.wasVisited = false }

        // building report
        val stringBuilder = StringBuilder()
        pathList.forEach { stringBuilder.append(it.toString() + "\n") }
        report(stringBuilder.toString())
    }

    /**
     * finds index of vertex which has shortest path from start vertex to it
     * and hasn't visited yet
     *
     * @param startVertexIndex - index of start vertex
     * @param paths - list of paths from start vertex to each vertex in this graph
     * @return - index of found vertex in adj matrix
     */
    private fun getMinWeightVertexIndex(
            startVertexIndex: Int, paths: MutableList<Path<T>>
    ): Int {
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

    /**
     * updates paths according to info about new visited vertex
     *
     * @param paths - list of all paths from start vertex
     * @param currentVertex - vertex that has just visited
     * @param previousPath - previous shortest path
     */
    private fun adjustPaths(
            paths: MutableList<Path<T>>,
            currentVertex: Vertex<T>,
            previousPath: Path<T>) {

        val parentIndex = vertexList.indexOf(currentVertex)
        val startToParentWeight = previousPath.weight

        for (i in 0 until currentVertexCount) {
            if (vertexList[i].wasVisited) continue

            val currentToDist = adjMatrix[parentIndex][i]
            val startToDist = startToParentWeight + currentToDist
            val path = paths[i].weight

            if (startToDist < path) {
                // update path
                paths[i].run {
                    addVertices(previousPath.pathPoints)
                    addVertex(currentVertex)
                    weight = startToDist
                }
            }
        }
    }

    /**
     * Represents shortest path from start vertex to vertex in the graph
     *
     * @param startVertex - vertex from that path is beginning
     * @param weight - summary weight from startVertex to vertex of this Path
     */
    private class Path<T>(startVertex: Vertex<T>, var weight: Long) {
        val pathPoints = mutableListOf<Vertex<T>>()
        private var currentVertex: Vertex<T>

        init {
            currentVertex = startVertex
        }

        /**
         * add new vertex through which the path lies
         */
        fun addVertex(vertex: Vertex<T>) {
            currentVertex = vertex
            pathPoints.add(vertex)
        }

        /**
         * add all previous vertices through which the path lies
         */
        fun addVertices(vertices: List<Vertex<T>>) {
            vertices.forEach {
                if (!pathPoints.contains(it)) pathPoints.add(it)
            }
        }

        override fun toString(): String {
            return pathPoints.joinToString("/") + weight
        }
    }
}