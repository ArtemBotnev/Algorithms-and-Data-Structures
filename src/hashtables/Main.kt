package hashtables

fun main(args: Array<String>) {
     fillAndCheckLinearHashTable()
}

private fun fillAndCheckLinearHashTable() {
    println("*** Linear Hash Table ***")
    val linearHashTable = LinearHashTable<String>(10).apply {
        put(54, "There")
        put(23, "are")
        put(12, "a")
        put(643, "lot")
        put(12, "of")
        put(25, "beautiful places")
        put(32, "in the world")
        put(158, "hurry")
        put(3, "to")
        put(73, "see")
        put(732, "them")
    }

    linearHashTable.printTableData()

    println()
    println("Get some elements")
    linearHashTable.apply {
        println(get(23))
        println(get(25))
        println(get(12))
        println(get(643))
        println(get(345))
    }

    linearHashTable.printTableData()

    println()
    println("Delete some elements")
    linearHashTable.apply {
        delete(12)
        delete(643)
        delete(111)
        delete(25)
        delete(23)
    }

    linearHashTable.printTableData()
}

//private fun <K, V> printTableData(table: HashTable<K, V>) {
//
//}

private fun <K, V> HashTable<K, V>.printTableData() {
    println(toString())
    println("Elements count: ${getCount()}")
    println("Fill factor: ${getFillFactor()}")
}