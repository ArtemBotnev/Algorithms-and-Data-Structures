package recursion

/**
 * creates anagrams for word
 *
 * @param word - input word
 * @return set anagrams of word
 */
fun createAnagrams(word: String): Set<String> {
    val charArray = word.toCharArray()
    val fullSize = charArray.size

    val result = mutableSetOf<String>()

    /**
     * moves far left letter of current char sequence to the right edge
     *
     * @param size - size of current array's part
     */
    fun shift(size: Int) {
        val position = fullSize - size
        val tempChar = charArray[position]

        for (i in position + 1 until fullSize) {
            charArray[i - 1] = charArray[i]
        }
        charArray[fullSize - 1] = tempChar
    }

    /**
     * creates anagram and adds one to result set
     *
     * @param size - size of current array's part
     */
    fun makeAnagram(size: Int) {
        if (size == 1) return

        // total count of anagrams equal to factorial number of letters
        // if the letters don't repeat
        for (i in 0 until size) {
            makeAnagram(size - 1)
            result.add(String(charArray))
            shift(size)
        }
    }

    makeAnagram(fullSize)

    return result
}