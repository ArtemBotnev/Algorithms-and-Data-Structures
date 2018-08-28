package stacksqueues

/**
 * Model of stack
 */
class CharStack(private val size: Int) {
    private val stackArray = Array(size) { '0' }
    private var top = -1

    /**
     * pushes element to stack and moved top to up
     *
     * @param char element what shout be pushed to stack
     */
    fun push(char: Char) = char.also { stackArray[++top] = it }

    /**
     * gets element from stack and moved top to down
     *
     * @return received element
     */
    fun pop() = stackArray[top--]

    /**
     * just get element, without moved top
     *
     * @return received element
     */
    fun peek() = stackArray[top]

    fun isEmpty() = top == -1

    fun isFull() = top == size - 1
}