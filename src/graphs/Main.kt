package graphs

fun main() {
    Graph(5).apply {
        addVertex('A')
        addVertex('B')
        addVertex('C')
        addVertex('D')
        addVertex('E')
        addVertex('C')
        addVertex('F')
        addVertex('G')
    }.also {
        print(it)
    }
}