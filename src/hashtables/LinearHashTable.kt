package hashtables

/**
 * Hash table
 * with collision resolution by open addressing with linear probing
 *
 * Integer for keys used to simplify
 */
class LinearHashTable<T>(size: Int) : OpenAddressingHashTable<T>(size) {
    override fun probingFunc(key: Int, keyHash: Int) = (keyHash + 1) % realSize
}