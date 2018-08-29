package stacksqueues

/**
 * Model of cycling queue
 */
class Queue(private val size: Int) {

    private val array = Array(size) { 0 }
    private var front = 0
    private var rear = -1
    private var count = 0

    /**
     * inserts element to queue
     *
     * @param element - what shout be insert to queue
     */
    fun insert(element: Int) {

        if (isFull()) throw ArrayIndexOutOfBoundsException(
                "Attempt to insert element into overloaded queue. Max size of queue = $size")

        if (rear == size - 1) rear = - 1
        array[++rear] = element
        count++
    }

    /**
     * gets element from top of queue and removes it
     *
     * @return top element
     */
    fun remove(): Int {
        val element = array[front++]
        if (front == size) front = 0
        count--

        return element
    }

    /**
     * just gets element, without removing it
     *
     * @return received element
     */
    fun peek() = array[front]

    fun isEmpty() = count == 0

    fun isFull() = count == size

    fun size() = size
}