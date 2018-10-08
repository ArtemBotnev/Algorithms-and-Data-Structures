package stacksqueues

/**
 * Model of stack
 */
interface Stack<T> {

    /**
     * pushes element to stack and moved top to up
     *
     * @param element element what shout be pushed to stack
     */
    fun push(element: T)

    /**
     * gets element from stack and moved top to down
     *
     * @return received element
     */
    fun pop(): T

    /**
     * just gets element, without moved top
     *
     * @return received element
     */
    fun peek(): T

    fun isEmpty(): Boolean

    fun isFull(): Boolean
}