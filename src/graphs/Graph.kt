package graphs

import java.util.*

/**
 * Model of graph
 */
class Graph<T>(private val maxVertexCount: Int) {

    private val vertexList = mutableListOf<Vertex<T>>()
    private val adjMatrix =
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
    fun addEdge(first: T, second: T): Boolean {
        val values = vertexList.map { it.value }
        val start = values.indexOf(first)
        val end = values.indexOf(second)

        if (start < 0 || end < 0) return false

        adjMatrix[start][end] = true
        adjMatrix[end][start] = true

        return true
    }

    /**
     * Depth First Search function
     *
     * @param action - lambda what perform an action with vertex value
     */
    fun dfs(action: (T) -> Unit) {
        val stack = Stack<Vertex<T>>()
        // start with first vertex
        val firstVertex = vertexList[0]
        firstVertex.wasVisited = true
        stack.push(firstVertex)
        action(firstVertex.value)

        while (!stack.empty()) {
            val indexTopVertex = vertexList.indexOf(stack.peek())
            val nextVertexIndex = getAdjUnvisitedVertex(indexTopVertex)

            // remove vertex from stack if it has no adjacent unvisited vertex
            if (nextVertexIndex < 0) {
                stack.pop()
            } else {
                val nextVertex = vertexList[nextVertexIndex]
                nextVertex.wasVisited = true
                stack.push(nextVertex)
                action(nextVertex.value)
            }
        }

        // stack is empty flags reset
        vertexList.forEach { it.wasVisited = false }
    }

    /**
     * Breadth First Search function
     *
     * @param action - lambda what perform an action with vertex value
     */
    fun bfs(action: (T) -> Unit) {
        val queue = LinkedList<Vertex<T>>() as Queue<Vertex<T>>
        // start with first vertex
        val firstVertex = vertexList[0]
        firstVertex.wasVisited = true
        queue.add(firstVertex)
        action(firstVertex.value)

        var indexOfCurrentVertex: Int

        while (!queue.isEmpty()) {
            val indexOfPreviousVertex = vertexList.indexOf(queue.remove())

            //while vertex has adjacent unvisited vertices
            while (getAdjUnvisitedVertex(indexOfPreviousVertex).also { indexOfCurrentVertex = it } > -1) {
                val currentVertex = vertexList[indexOfCurrentVertex]
                currentVertex.wasVisited = true
                // insert adjacent vertex to queue
                queue.add(currentVertex)
                action(currentVertex.value)
            }
        }

        // queue is empty flags reset
        vertexList.forEach { it.wasVisited = false }
    }

    /**
     * finds first not visited vertex, adjacent with current vertex
     *
     * @param firstIndex - index of current vertex in adjMatrix
     * @return index of vertex which is not visited vertex, adjacent with current vertex
     */
    private fun getAdjUnvisitedVertex(firstIndex: Int): Int {
        vertexList.forEachIndexed { secondIndex, vertex ->
            if (adjMatrix[firstIndex][secondIndex] && !vertex.wasVisited) {
                return secondIndex
            }
        }

        return -1
    }

    override fun toString() = vertexList.joinToString(",") { it.value.toString() }

    /**
     * inner class graph's vertex
     * container for any object type (value)
     */
    private inner class Vertex<T>(val value: T, var wasVisited: Boolean = false) {
        override fun toString() = value.toString()
    }
}