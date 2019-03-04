package hashtables

/**
 * Hash table
 * with collision resolution by chain method (this example uses list)
 *
 * Integer for keys used to simplify
 * It just puts new value with the same key into,
 * but returns or deletes first of them
 */
class ChainHashTable<T>(val size: Int) : HashTable<Int, T> {

    // array of lists as items, each list is collection of pair (key, value)
    private val hashArray =
            Array<MutableList<Pair<Int, T?>>>(size) { mutableListOf() }

    private var count: Int = 0

    override fun hash(key: Int) = key % size

    override fun put(key: Int, value: T?) {
        count++

        val newPair = Pair(key, value)
        val keyHash = hash(key)
        // just add new pair to list with match keyHash in array
        hashArray[keyHash].add(newPair)
    }

    override fun get(key: Int): T? {
        val keyHash = hash(key)
        // find list with in array by hash
        val list = hashArray[keyHash]
        // find element into list according to this key
        // (first element with this key)!!!
        list.forEach {
            if (it.first == key) return it.second
        }
        // elements with this key not found
        return null
    }

    override fun delete(key: Int): T? {
        val keyHash = hash(key)
        // find list with in array by hash
        val list = hashArray[keyHash]
        // find element into list according to this key
        // (first element with this key)!!!
        list.forEach {
            if (it.first == key) {
                val tempValue = it.second
                // delete element from list
                list.remove(it)
                count--
                return tempValue
            }
        }
        // elements with this key not found, nothing to delete
        return null
    }

    override fun getCount() = count

    // can be more than 1.0 for this table but recommended not more than 3.0
    override fun getFillFactor() = count.toFloat() / size

    override fun toString() = hashArray
            .joinToString(prefix = "{", postfix = "}", separator = "; ") { list ->
                list.joinToString(prefix = "[", postfix = "]", separator = ", ") { pair ->
                    "key=${pair.first} value=${pair.second}"
                }
            }
}