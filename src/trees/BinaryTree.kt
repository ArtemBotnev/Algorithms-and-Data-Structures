package trees

/**
 * Model of binary tree
 *
 * Integer for keys used to simplify
 * Not supports key's duplicates
 */
class BinaryTree<V> {

    // root node of tree
    private var root: Node<V>? = null

    /**
     * puts new pair (key, value) to tree
     *
     * @param key ...
     * @param value ...
     * @return true if operation success, false if there is already this key
     */
    fun put(key: Int, value: V): Boolean {
        val newNode = Node(key, value)

        if (root != null) {
            var parent: Node<V>
            var current: Node<V>? = root

            while (current != null) {
                parent = current

                when {
                    key < current.key -> {
                        // move to left child
                        current = current.leftChild
                        if (current == null) {
                            parent.leftChild = newNode
                            return true
                        }
                    }
                    key > current.key  -> {
                        // move to right child
                        current = current.rightChild
                        if (current == null) {
                            parent.rightChild = newNode
                            return true
                        }
                    }
                    // keys' duplicate
                    else -> return false
                }
            }
        } else {
            // tree is empty
            root = newNode
            return true
        }

        return false
    }

    /**
     * gets value which corresponds to this key
     *
     * @param key ...
     * @return value which corresponds to this key or
     * null if no value corresponds to one
     */
    fun get(key: Int): V? {
        var current = root

        while (current != null && current.key != key) {
            current = if (key < current.key) {
                current.leftChild
            } else {
                current.rightChild
            }
        }

        return current?.data
    }

    /**
     * Tree's main unit (container for data)
     */
    private inner class Node<T>(val key: Int, var data: T) {
        var leftChild: Node<T>? = null
        var rightChild: Node<T>? = null
    }
}