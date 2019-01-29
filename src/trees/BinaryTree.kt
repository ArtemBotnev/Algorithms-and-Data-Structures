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
     * deletes node by its key and returns it data
     *
     * @param key of node which will be deleted
     * @return data of deleted node or null if node wasn't found
     */
    fun delete(key: Int): V? {
        var current = root
        var parent = root
        var isLeftChild = true

        while (current != null && current!!.key != key) {
            parent = current
            if (key < current.key) {
                isLeftChild = true
                current = current.leftChild
            } else {
                isLeftChild = false
                current = current.rightChild
            }
        }

        if (current == null) return null

        // deleted node has only left child
        when {
            current.rightChild == null -> when {
                current === root -> root = current.leftChild
                isLeftChild -> parent?.leftChild = current.leftChild
                else -> parent?.rightChild = current.leftChild
            }
            // deleted node has only right child
            current.leftChild == null -> when {
                current === root -> root = current.rightChild
                isLeftChild -> parent?.leftChild = current.rightChild
                else -> parent?.rightChild = current.rightChild
            }
            // deleted node has both children
            else -> {
                var successor = getSuccessor(current)

                when {
                    current === root -> root = successor
                    isLeftChild -> parent?.leftChild = successor
                    else -> parent?.rightChild = successor
                }

                successor.leftChild = current.leftChild
            }
        }

        return current.data
    }

    /**
     * gets successor of node which will be deleted
     *
     * @param node which is deleting
     * @return successor of deleting node
     */
    private fun getSuccessor(node: Node<V>): Node<V> {
        var parent = node
        var successor = node
        // move to right child
        var current = node.rightChild
        // while there are left children
        while (current != null) {
            parent = successor
            successor = current
            // move ot left child
            current = current.leftChild
        }

        // shift
        if (successor != node.rightChild) {
            parent.leftChild = successor.rightChild
            successor.rightChild = node.rightChild
        }

        return successor
    }

    /**
     * Tree's main unit (container for data)
     */
    private inner class Node<T>(val key: Int, var data: T) {
        var leftChild: Node<T>? = null
        var rightChild: Node<T>? = null
    }
}