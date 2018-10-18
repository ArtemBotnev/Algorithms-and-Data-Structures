package linkedlists

fun main(args: Array<String>) {
    linkedStackExample()
}

private fun linkedStackExample() {
    println("Revers string with linked stack:")
    println()
    //reverse the string with linked a stack
    val string = "Kotlin is a great language!"
    val stack = LinkedStack<Char>()
    //put elements to stack
    string.forEach { stack.push(it) }
    //get elements and print
    while (!stack.isEmpty()) print(stack.pop())
    repeat(2) { println() }
}