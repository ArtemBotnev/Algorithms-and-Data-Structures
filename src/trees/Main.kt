package trees

fun main(args: Array<String>) {
    fillTreeAndCheckData()
}

/**
 * uses country calling codes as keys and country names as data
 */
private fun fillTreeAndCheckData() =
        BinaryTree<String>()
                .apply {
                    put(7, "Russia")
                    put(1, "USA")
                    put(658, "Jamaica")
                    put(20, "Egypt")
                    put(261, "Madagascar")
                    put(34, "Spain")
                    put(34, "This just for checking duplicates")
                    put(357, "Cyprus")
                    put(679, "Fiji")
                    put(507, "Panama")
                    put(-100, "It's enough")
                }.apply {
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
