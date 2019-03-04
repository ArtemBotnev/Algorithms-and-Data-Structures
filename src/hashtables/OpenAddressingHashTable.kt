package hashtables

/**
 * Abstract hash table
 * with collision resolution by open addressing
 *
 * Integer for keys used to simplify
 * It rewrites previous value when new value with the same key puts into
 */
abstract class OpenAddressingHashTable<T>(protected val size: Int)
    : HashTable<Int, T> {

    // doubling size and find next prime number for effective open addressing
    protected val realSize = getNextPrimeNumber(size shl 1)
    private val hashArray = Array<Pair<Int, T?>?>(realSize) { null }

    // numbers of element in table (should be not more than size)
    private var count: Int = 0

    /**
     * reflects probing method
     *
     * @param key - key of value
     * @param keyHash - calculated key's hash
     *
     * @return real position in hash table according keyHash
     */
    protected abstract fun probingFunc(key: Int, keyHash: Int): Int

    override fun hash(key: Int) = key % realSize

    override fun put(key: Int, value: T?) {
        if (count == size) throw IndexOutOfBoundsException(
                "Count of elements (OpenAddressingHashTable) " +
                        "must be not more than table size. Size is $size"
        )

        var keyHash = hash(key)
        // linear probing
        while (hashArray[keyHash] != null) {
            // new value for this key
            if (hashArray[keyHash]!!.first == key) {
                hashArray[keyHash] = Pair(key, value)
                return
            }
            // move to next cell
            keyHash = probingFunc(key, keyHash)
        }
        hashArray[keyHash] = Pair(key, value)
        count++
    }

    override fun get(key: Int): T? {
        var keyHash = hash(key)
        // try until empty cell
        while (hashArray[keyHash] != null) {
            if (hashArray[keyHash]!!.first == key) {
                return hashArray[keyHash]?.second
            }
            keyHash = probingFunc(key, keyHash)
        }

        return null
    }

    override fun delete(key: Int): T? {
        var keyHash = hash(key)

        while (hashArray[keyHash] != null) {
            if (hashArray[keyHash]!!.first == key) {
                val temp = hashArray[keyHash]?.second
                hashArray[keyHash] = null
                count--
                return temp
            }
            keyHash = probingFunc(key, keyHash)
        }

        return null
    }

    override fun getCount() = count

    // can't be more than 0.5 for this table
    override fun getFillFactor() = count.toFloat() / realSize

    override fun toString() = hashArray
            .filterNotNull()
//            .map { it.second }
            .map { it.second ?: "x" }
            .joinToString(separator = "; ")

    /**
     * finds next prime number after number in arg
     *
     * @param number - current number
     * @return current number if it's a prime or next prime number after it
     */
    private fun getNextPrimeNumber(number: Int): Int {
        // return true if number is prime
        fun checkNumber(): Boolean {
            if (number < 2) return false

            for (i in 2..number / 2) {
                if (number % i == 0) return false
            }

            return true
        }

        var result = number
        if (!checkNumber()) {
            result = getNextPrimeNumber(number + 1)
        }

        return result
    }
}