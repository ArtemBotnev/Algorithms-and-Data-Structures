package hashtables

fun main(args: Array<String>) {
     val linearHashTable = LinearHashTable<String>(7).apply {
         put(54, "There")
         put(23, "are")
         put(12, "a")
         put(643, "lot")
         put(12, "of")
         put(25, "beautiful places")
         put(32, "in the world")
     }

    println()
    linearHashTable.apply {
        println(get(23))
        println(get(25))
        println(get(12))
        println(get(643))
        println(get(345))
    }

    println()
    println(linearHashTable.toString())

    linearHashTable.apply {
        delete(12)
        delete(643)
        delete(111)
        delete(25)
        delete(23)
    }

    println()
    println(linearHashTable.toString())
}