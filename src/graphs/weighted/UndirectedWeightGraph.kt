package graphs.weighted

class UndirectedWeightGraph<T>(maxVertexCount: Int, infinity: Long)
    : WeightedGraph<T>(maxVertexCount, infinity) {

    override fun addEdge(first: T, second: T, weight: Long): Boolean {
        return true
    }
}