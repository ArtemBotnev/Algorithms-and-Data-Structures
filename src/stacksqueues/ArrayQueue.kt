package stacksqueues

/**
 * Model of cycling array queue
 */
class ArrayQueue(private val size: Int): Queue<Int> {

    private val array = Array(size) { 0 }
    private var front = 0
    private var rear = -1
    private var count = 0

    override fun insert(element: Int) {

        if (isFull()) throw ArrayIndexOutOfBoundsException(
                "Attempt to insert element into overloaded queue. Max size of queue = $size")

        if (rear == size - 1) rear = - 1
        array[++rear] = element
        count++
    }

    override fun remove(): Int {
        val element = array[front++]
        if (front == size) front = 0
        count--

        return element
    }

    override fun peek() = array[front]

    override fun isEmpty() = count == 0

    override fun isFull() = count == size

    override fun size() = size
}