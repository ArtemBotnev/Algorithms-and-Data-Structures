package trees

fun main(args: Array<String>) {
    val tree = fillTreeAndCheckData()
    println(tree)

    println()
    tree.deleteItemAndCheck(261)
    println(tree)
    println()
    tree.deleteItemAndCheck(-100)
    println(tree)
    println()
    tree.deleteItemAndCheck(7)
    println(tree)
    println()
    tree.deleteItemAndCheck(1)
    println(tree)
    println()
    tree.deleteItemAndCheck(357)
    println(tree)
    println()
    tree.deleteItemAndCheck(507)
    println(tree)
}

/**
 * uses country calling codes as keys and country names as data
 */
private fun fillTreeAndCheckData() =
        BinaryTree<String>()
                .apply {
                    put(261, "Madagascar")
                    put(507, "Panama")
                    put(7, "Russia")
                    put(34, "Spain")
                    put(1, "USA")
                    put(658, "Jamaica")
                    put(20, "Egypt")
                    put(34, "This just for checking duplicates")
                    put(357, "Cyprus")
                    put(679, "Fiji")
                    put(-100, "It's enough")
                }.apply {
                    printSpecificTree()
                }

/**
 * deletes item by its key from binary tree
 *
 * @param id(key) of item(node) which will be deleted
 */
private fun <T> BinaryTree<T>.deleteItemAndCheck(id: Int) {
    this.delete(id)
    this.printSpecificTree()
}

/**
 * prints data only from tree in this example
 */
private fun <T> BinaryTree<T>.printSpecificTree() {
    this.apply {
        println(get(63))
        println(get(507))
        println(get(7))
        println(get(34))
        println(get(357))
        println(get(1))
        println(get(658))
        println(get(261))
        println(get(20))
        println(get(679))
        println(get(-12))
        println(get(-100))
    }
}