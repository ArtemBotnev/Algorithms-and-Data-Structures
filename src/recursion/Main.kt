package recursion

fun main(args: Array<String>) {
    println()
    // anagram of word "java"
    val javaAnagram = createAnagrams("java")
    println(javaAnagram)
    printAnagramsCount(javaAnagram)

    println()
    // anagram of word "kotlin"
    val kotlinAnagram = createAnagrams("kotlin")
    println(kotlinAnagram)
    printAnagramsCount(kotlinAnagram)
}

private fun printAnagramsCount(anagrams: Set<String>) =
        println("Total count of anagrams = ${anagrams.size}")