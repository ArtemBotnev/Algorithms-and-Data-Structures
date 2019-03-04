package hashtables

interface HashTable<in K, V> {

    /**
     * puts pair (key, value) to hash table
     *
     * @param key - type K
     * @param value - type V
     */
    fun put(key: K, value: V?)

    /**
     * gets value from hash table by it's specific key
     *
     * @param key - which corresponds to value
     * @return value V or null if there isn't value with that key
     */
    fun get(key: K): V?

    /**
     * deletes value from hash table by it's specific key and return it
     *
     * @param key - which corresponds to value
     * @return value V or null if there isn't value with that key
     */
    fun delete(key: K): V?

    /**
     * hash function calculates hash for key
     *
     * @param key - for which will be calculate hash
     * @return hash code
     */
    fun hash(key: K): Int

    /**
     * @return count of elements in table
     */
    fun getCount(): Int

    /**
     * @return count of element to table size
     */
    fun getFillFactor(): Float
}