package hashtables

/**
 * Hash table
 * with collision resolution by open addressing with linear probing
 *
 * Integer for keys used to simplify
 */
class LinearHashTable<T>(val size: Int) : HashTable<Int, T> {

    // doubling size for effective open addressing
    private val realSize = size shl 1
    private val hashArray = Array<Pair<Int, T?>?>(realSize) { null }

    private fun hash(key: Int) = key % realSize

    override fun put(key: Int, value: T?) {
        var keyHash = hash(key)
        // linear probing
        while (hashArray[keyHash] != null) {
            // new value for this key
            if (hashArray[keyHash]!!.first == key) {
                hashArray[keyHash] = Pair(key, value)
                return
            }
            // move to next cell
            keyHash++
            keyHash %= realSize
        }
        hashArray[keyHash] = Pair(key, value)
    }

    override fun get(key: Int): T? {
        var keyHash = hash(key)
        // try until empty cell
        while (hashArray[keyHash] != null) {
            if (hashArray[keyHash]!!.first == key) {
                return hashArray[keyHash]?.second
            }
            keyHash++
            keyHash %= realSize
        }

        return null
    }

    override fun delete(key: Int): T? {
        var keyHash = hash(key)

        while (hashArray[keyHash] != null) {
            if (hashArray[keyHash]!!.first == key) {
                val temp = hashArray[keyHash]?.second
                hashArray[keyHash] = null
                return temp
            }
            keyHash++
            keyHash %= realSize
        }

        return null
    }

    override fun toString() = hashArray
            .filterNotNull()
            .map { it.second }
            .joinToString(separator = ", ")
}