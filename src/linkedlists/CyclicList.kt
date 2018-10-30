package linkedlists

/**
 * Model of cyclic list
 */
class CyclicList<T> {
    var current: Link<T>? = null
        private set

    private var previous: Link<T>? = null
    private var head: Link<T>? = null
    private var tail: Link<T>? = null

    /**
     * adds data element to list
     *
     * @param data - element which will be added to list
     */
    fun add(data: T) = when {
        head == null -> {
            head = Link(data, null)
            current = head
        }
        tail == null -> {
            tail = Link(data, head)
            head?.next = tail
        }
        else -> {
            val temp = Link(data, head)
            tail?.next = temp
            tail = temp
        }
    }

    /**
     * moves to the next element and make it current
     */
    fun step() {
        previous = current
        current = current?.next
    }

    /**
     * gets current data element from list
     *
     * @return current data from list
     */
    fun get() = current?.data

    /**
     * gets current data element, deletes it and moves to next element
     *
     * @return current data from list
     */
    fun deleteCurrent(): T? {
        val date = current?.data
        current = current?.next
        // no link points to deleted element
        previous?.next = current

        // check if we have only one element in list
        if (previous === current) tail = null

        return date
    }

    fun isEmpty() = current == null

    fun hasOnlyOne() = tail == null

    /**
     * Inner class Link (container for data)
     */
    inner class Link<T>(val data: T, var next: Link<T>? /*val position: Long*/)
}