package stacksqueues

fun main(args: Array<String>) {
    stackExample()
    queueExample()
    checkBrackets()
}

private fun stackExample() {
    println("Revers string with stack:")
    println()
    //reverse the string with a stack
    val string = "Kotlin is island"
    val stack = CharStack(string.length)
    //put elements to stack
    string.forEach { stack.push(it) }
    //get elements and print
    while (!stack.isEmpty()) print(stack.pop())
    repeat(2) { println() }
}

private fun queueExample() {
    println("Queue example:")
    println()
    // create int array
    val intArray = Array(15) { it * 3 - (it.rem(5)) }
    println(intArray.joinToString(separator = ", ") { it.toString() })

    val queue = Queue(intArray.size)
    // put all string to queue
    intArray.forEach { queue.insert(it) }

    //print element on top
    println(queue.peek())

    // attempt to put one more element
    try {
        queue.insert(67)
    } catch (e: ArrayIndexOutOfBoundsException) {
        e.printStackTrace()
        println(e.message)
    }

    // get seven seven elements
    val list = mutableSetOf<Int>()
    repeat(7) { list.add(queue.remove()) }
    println(list.joinToString(separator = ", ") { it.toString() })

    // add to elements
    queue.insert(85)
    queue.insert(63)
    println(queue.remove())
    println()
}

private fun checkBrackets() {
    println("Check brackets with stack:")
    println()

    var message: String

    message = BracketsChecker.check("} Hello!")
    println(message)
    message = BracketsChecker.check("[This (is new] sentence)")
    println(message)
    message = BracketsChecker.check("{Here [is no errors (with brackets)]}")
    println(message)
}