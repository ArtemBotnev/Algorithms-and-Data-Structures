package hashtables

/**
 * Hash table
 * with collision resolution by open addressing with hash probing
 *
 * Integer for keys used to simplify
 */
class DoubleHashTable<T>(size: Int) : OpenAddressingHashTable<T>(size) {

    override fun probingFunc(key: Int, keyHash: Int): Int {
        val stepSize = 5 - key % 5 // step size by hash function

        return (keyHash + stepSize) % realSize
    }
}