package stacksqueues

/**
 * Model of queue
 */
interface Queue<T> {

    /**
     * inserts element to queue
     *
     * @param element - what shout be insert to queue
     */
    fun insert(element: T)

    /**
     * gets element from top of queue and removes it
     *
     * @return top element
     */
    fun remove(): T

    /**
     * just gets element, without removing it
     *
     * @return received element
     */
    fun peek(): T

    fun isEmpty(): Boolean

    fun isFull(): Boolean

    fun size(): Int
}