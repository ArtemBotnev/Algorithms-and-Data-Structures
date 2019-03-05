package hashtables

fun main(args: Array<String>) {
    val linearHashTable = LinearHashTable<String>(10)
    fillTableAndCheck(linearHashTable, "Linear")
    val doubleHashTable = DoubleHashTable<String>(10)
    fillTableAndCheck(doubleHashTable, "Double")
    val chainHashTable = ChainHashTable<String>(8)
    fillTableAndCheck(chainHashTable, "Chain")
}

private fun fillTableAndCheck(table: HashTable<Int, String>, title: String) {
    println()
    println("*** $title Hash Table ***")
    table.apply {
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

    println()
    table.printTableData()

    println()
    println("Get some elements")
    table.apply {
        println(get(23))
        println(get(25))
        println(get(12))
        println(get(643))
        println(get(345))
    }

    table.printTableData()

    println()
    println("Delete some elements")
    table.apply {
        delete(12)
        delete(643)
        delete(111)
        delete(25)
        delete(23)
    }

    table.printTableData()
}

private fun <K, V> HashTable<K, V>.printTableData() {
    println(toString())
    println("Elements count: ${getCount()}")
    println(String.format("Fill factor: %.2f", getFillFactor()))
}