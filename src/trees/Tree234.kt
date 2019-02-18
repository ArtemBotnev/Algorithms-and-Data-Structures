package trees

/**
 * Model of 2 3 4 tree which is kind of B trees
 *
 * Not supports key's duplicates
 */
class Tree234<K : Comparable<K>, V> {

    companion object {
        private const val ORDER = 4
    }

    private var root = Node()

     /**
     * finds position of data with key K into its node
      *
      * @param key - data key K
      * @return position of data into node or -1 if there aren't node's children
     */
    fun find(key: K): Int {
        var currentNode = root
        var childIndex: Int

        while (true) {
            childIndex = currentNode.getIndex(key)
            when {
                childIndex != -1 -> return childIndex
                currentNode.isLeaf -> return -1
                else -> currentNode = getNextChild(currentNode, key)
            }
        }
    }

    fun put(key: K, value: V) {
        var currentNode = root
        val data = Pair(key, value)

        while (true) {
            if (currentNode.isFull) {
                split(currentNode)
                currentNode = currentNode.parent
                currentNode = getNextChild(currentNode, key)
            } else if (currentNode.isLeaf) {
                break
            } else {
                currentNode = getNextChild(currentNode, key)
            }

            currentNode.put(data)
        }
    }

    private fun getNextChild(parent: Node, key: K): Node {
        for (i in 0 until parent.itemsCount) {
            // this parent node has at least one child because it isn't leaf
            if (parent[i].first == null) break

            if (key < parent[i].first!!) {
                return parent.getChild(i)!!
            }
        }

        return parent.getChild(0)!!
    }

    private fun split(node: Node) {
        val parent: Node

        // parent, second and third child of this node
        val thirdData = node.delete()
        val secondData = node.delete()

        // unbind second and third child from this node
        val secondChild = node.unbindChild(2)
        val thirdChild = node.unbindChild(3)

        // create new node
        val newNode = Node()

        // in case if this node is a root, we have to create new root
        if(node === root) {
            root = Node()
            root.bindChild(0, node)
        } else {
            parent = node.parent
            // put second data item (this node is full all cells aren't null)
            val index = parent.put(secondData!!)
            // move parent's items
            for (i in parent.itemsCount - 1 downTo index) {
                val childNode = parent.unbindChild(i)
                parent.bindChild(i, childNode)
            }

            // bind new node with parent
            parent.bindChild(index + 1, newNode)
            // bind data and child with new node
            // (this node is full all cells aren't null)
            newNode.apply {
                put(thirdData!!)
                bindChild(0, secondChild)
                bindChild(1, thirdChild)
            }
        }
    }

    /**
     * Tree's main unit (container for data)
     */
    private inner class Node {
        // children nodes of this current node
        private val children: MutableList<Node?> = ArrayList(ORDER)
        // list of data items of this current node
        private val dataList: MutableList<Pair<K?, V?>> = ArrayList(ORDER - 1)
        // current count of data items
        var itemsCount = 0
            private set

        lateinit var parent: Node
            private set

        var isLeaf = true
            private set
            get() = children[0] == null

        var isFull = false
            private set
            get() = itemsCount == ORDER - 1

        /**
         * gets data by its index
         *
         * @param index - index of data in this node
         * @return data according to index
         */
        operator fun get(index: Int) = dataList[index]

        /**
         * gets child by its index
         *
         * @param index - index of child of this node
         * @return child Node according to index
         */
        fun getChild(index: Int) = children[index]

        /**
         * binds child with this node according to index
         *
         * @param index - child index for new bound child
         * @param child - child node
         */
        fun bindChild(index: Int, child: Node?) {
            children[index] = child
            child?.let { it.parent = this }
        }

        /**
         * unbinds child from this node according to index
         *
         * @param index - index of child which will be unbound
         */
        fun unbindChild(index: Int): Node? {
            val node = children[index]
            children[index] = null

            return node
        }

        /**
         * gets index of specific data by key K
         *
         * @param key - key K
         * @return data index or -1 if node doesn't contains this data
         */
        fun getIndex(key: K): Int {
            for (i in 0 until ORDER - 1) {
                if (dataList[i].first == null) break

                if (dataList[i].first == key) return i
            }

            return -1
        }

        /**
         * puts data to node
         *
         * @param data - data which is pair: key, value
         * @return data index in this node
         */
        fun put(data: Pair<K, V?>): Int {
            itemsCount++

            for (i in ORDER - 2 downTo 1) {
                if (dataList[i].first == null) {
                    continue
                } else {
                    if (data.first < dataList[i].first!!) {
                        dataList[i + 1] = dataList[i]
                    } else if (data.first > dataList[i].first!!) {
                        dataList[i + 1] = data
                        return i + 1
                    }
                }
            }
            dataList[0] = data

            return 0
        }

        /**
         * delete biggest element (element from right edge)
         *
         * @return data pair that was deleted
         */
        fun delete(): Pair<K, V?>? {
            val data = dataList[itemsCount - 1]
            dataList[itemsCount - 1] = Pair(null, null)
            itemsCount--

            return if (data.first == null) null
                else data as Pair<K, V?>
        }
    }
}