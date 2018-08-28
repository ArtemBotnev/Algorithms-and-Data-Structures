package stacksqueues

fun main(args: Array<String>) {
    //reverse the string with a stack
    val string = "Kotlin is island"
    val stack = CharStack(string.length)
    //put elements to stack
    string.forEach { stack.push(it) }
    //get elements and print
    while (!stack.isEmpty()) print(stack.pop())
    println()
}