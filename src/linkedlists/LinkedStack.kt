package linkedlists

import stacksqueues.Stack

/**
 * Stack implementation with linked list
 */
class LinkedStack<T> : Stack<T> {

    private val list = BindList<T>()

    override fun push(element: T) {
        list.addFirst(element)
    }

    override fun pop() = list.deleteFirst()

    override fun peek() = list.getFirst()

    override fun isEmpty() = list.isEmpty()

    //can consider linked list as almost endless
    override fun isFull() = false
}