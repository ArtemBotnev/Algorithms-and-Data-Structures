package stacksqueues

/**
 * Array stack implementation
 */
class CharStack(private val size: Int): Stack<Char> {

    private val stackArray = Array(size) { '0' }
    private var top = -1

    override fun push(char: Char) {
        char.also { stackArray[++top] = it }
    }

    override fun pop() = stackArray[top--]

    override fun peek() = stackArray[top]

    override fun isEmpty() = top == -1

    override fun isFull() = top == size - 1
}