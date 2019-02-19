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
      * gets data value - V by its key - K from tree
      *
      * @param key - data key K
      * @return value according this key
      * or null if tree isn't contains this pair (key value)
     */
    fun get(key: K): V? {
        var currentNode = root
        var dataIndex: Int

        while (true) {
            // trying to find data into current node
            dataIndex = currentNode.getIndex(key)
            when {
                // success data is into current node
                dataIndex != -1 -> return currentNode[dataIndex]?.second
                // this is leaf node and it isn't contains match data
                currentNode.isLeaf -> return null
                // go to nex child
                else -> currentNode = getNextChild(currentNode, key)
            }
        }
    }

    /**
     * put pair (key value) into this tree
     *
     * @param key - key of data which should be stored
     * @param value - data which should be stored in this tree
     */
    fun put(key: K, value: V?) {
        var currentNode = root
        val data = Pair(key, value)

        while (true) {
            if (currentNode.isFull) {
                // split it
                split(currentNode)
                // move up to parent
                currentNode = currentNode.parent
                currentNode = getNextChild(currentNode, key)
            } else if (currentNode.isLeaf) {
                break
            } else {
                // go toward leaf node
                currentNode = getNextChild(currentNode, key)
            }
        }
        currentNode.put(data)
    }

    /**
     * finds child of node which can contains data with key
     *
     * @param parent - node child of which should be received
     * @param key - of data by which can be found appropriate child node
     * @return child node which best fit of key
     */
    private fun getNextChild(parent: Node, key: K): Node {
        var i = 0
        while (i < parent.itemsCount) {
            // this parent node has at least one child because it isn't leaf
            if (parent[i] == null) break

            if (key < parent[i]?.first!!) {
                return parent.getChild(i)!!
            }
            i++
        }

        return parent.getChild(i)!!
    }

    /**
     * splits node to two and share its data between parent and new right node
     *
     * @param node - which should be split
     */
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
            parent = root
            root.bindChild(0, node)
        } else {
            parent = node.parent
        }

        // put second data item (this node is full all cells aren't null)
        val index = parent.put(secondData!!)
        // move parent's items
        for (i in parent.itemsCount - 1 downTo index + 1) {
            val childNode = parent.unbindChild(i)
            parent.bindChild(i + 1, childNode)
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

    /**
     * Tree's main unit (container for data)
     */
    private inner class Node {
        // children nodes of this current node
        private val children: MutableList<Node?> = MutableList(ORDER) { null }
        // list of data items of this current node
        private val dataList: MutableList<Pair<K, V?>?> =
                MutableList(ORDER - 1) { null }
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
                if (dataList[i] == null) break

                if (dataList[i]?.first == key) return i
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

            for (i in ORDER - 2 downTo 0) {
                if (dataList[i] == null) {
                    continue
                } else {
                    if (data.first < dataList[i]?.first!!) {
                        dataList[i + 1] = dataList[i]
                    } else {
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
            dataList[itemsCount - 1] = null
            itemsCount--

            return data
        }
    }
}