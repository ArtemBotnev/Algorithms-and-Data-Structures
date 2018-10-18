package linkedlists

/**
 * Model of linked list
 */
class BindList<T> {

    private var first: Link<T>? = null

    /**
     * adds data element to list
     *
     * @param data - element which will be added as first to list
     */
    fun addFirst(data: T){
        first = if (first != null) Link(data, first) else Link(data, null)
    }

    /**
     * get first data element from list
     *
     * @return first data from list
     */
    fun getFirst() = first?.data

    /**
     * get first data element from list and deletes it
     *
     * @return first data from list
     */
    fun deleteFirst(): T? {
        val date = first?.data
        first = first?.next

        return date
    }

    fun isEmpty() = first == null

    /**
     * Inner class Link (container for data)
     */
    inner class Link<T>(val data: T, var next: Link<T>?)
}