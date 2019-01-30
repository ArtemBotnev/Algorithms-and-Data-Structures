package trees

import java.lang.StringBuilder
import kotlin.math.pow

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

        while (current != null && current.key != key) {
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
                val successor = getSuccessor(current)

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
     * gets max level (deep) of this tree
     *
     * @return max level of tree
     */
    fun getMaxLevel(): Int {
        var currentLevel = 0
        var maxLevel = 0

        fun <T> stepTo(node: Node<T>?) {
            if (node == null) return

            currentLevel++
            stepTo(node.leftChild)
            if (maxLevel < currentLevel) maxLevel = currentLevel
            currentLevel--

            currentLevel++
            stepTo(node.rightChild)
            if (maxLevel < currentLevel) maxLevel = currentLevel
            currentLevel--
        }

        stepTo(root)

        return maxLevel
    }

    override fun toString() = showTreeAsString()

    /**
     * creates string which is visual presentation of this tree
     *
     * @return arrays matrix as a string
     */
    private fun showTreeAsString(): String {
        val emptyCell = "_"
        // create matrix field for showing tree
        val treeDeep = getMaxLevel()
        val horizontalSize = 2.pow(treeDeep)
        val matrix = Array(treeDeep) { Array(horizontalSize) { emptyCell } }

        /**
         * added each tree's node to matrix according to position of one
         *
         * @param node current node
         * @param vertical position if this node
         * @param horizontal position if this node
         */
        fun <T> stepTo(node: Node<T>?, vertical: Int, horizontal: Int) {
            if (node == null) return
            // to left child
            stepTo(
                    node.leftChild,
                    vertical + 1,
                    horizontal - 2.pow(treeDeep - vertical - 2)
            )
            // to right child
            stepTo(
                    node.rightChild,
                    vertical + 1,
                    horizontal + 2.pow(treeDeep - vertical - 2)
            )

            matrix[vertical][horizontal] = node.key.toString()
        }

        stepTo(root, 0, horizontalSize / 2 - 1)

        val result = StringBuilder()
        // create string from matrix
        matrix.forEach {
            result.append(it.joinToString("   ")).append('\n')
        }

        return result.toString()
    }

    /**
     * pow for Int type
     */
    private fun Int.pow(exp: Int) = this.toFloat().pow(exp).toInt()

    /**
     * Tree's main unit (container for data)
     */
    private inner class Node<T>(val key: Int, var data: T) {
        var leftChild: Node<T>? = null
        var rightChild: Node<T>? = null
    }
}