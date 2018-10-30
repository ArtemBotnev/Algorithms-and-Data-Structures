package linkedlists

fun main(args: Array<String>) {
    linkedStackExample()

    //several examples of solution of J.F. task
    josephusFlaviousTask(15, 3)
    josephusFlaviousTask(342, 24)
    josephusFlaviousTask(3, 15)
    josephusFlaviousTask(34, 2)
    josephusFlaviousTask(98, 12)
}

private fun linkedStackExample() {
    println("Revers string with linked stack:")
    //reverse the string with linked a stack
    val string = "Kotlin is a great language!"
    val stack = LinkedStack<Char>()
    //put elements to stack
    string.forEach { stack.push(it) }
    //get elements and print
    while (!stack.isEmpty()) print(stack.pop())
    repeat(2) { println() }
}

/**
 * Solution on Josephus Flavious task using cyclic list
 *
 * @param count - count of members
 * @param stepSize - step through which members will be removed, must be more than 1
 * @return number of member which will remain
 */
private fun josephusFlaviousTask(count: Int, stepSize: Int): Int {
    if (stepSize < 2) return 0

    println("Josephus Flavious Task: members - $count, every $stepSize out")

    val cyclicList = CyclicList<Int>()
    for (i in 1..count) {
        cyclicList.add(i)
    }

    while (!cyclicList.hasOnlyOne()) {
        repeat(stepSize - 1) { cyclicList.step() }
        cyclicList.deleteCurrent()
    }

    val stayed = cyclicList.current?.data ?: 0

    println("stayed: $stayed")

    return stayed
}