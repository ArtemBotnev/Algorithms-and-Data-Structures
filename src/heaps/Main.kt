package heaps

import kotlin.random.Random

private const val ELEMENTS_COUNT = 25

fun main() {
    val valueList = mutableListOf<Int>()

    // add random elements
    for (i in 0..ELEMENTS_COUNT) {
        valueList.add(Random.nextInt(0, 200))
    }

    testPriorityQueue(valueList)
    println()
    sort(valueList)
}

private fun testPriorityQueue(valueList: List<Int>) {
    println("Source list:\n ${valueList.showList()}")

    val priorityQueue = Heap(ELEMENTS_COUNT + 1)
    // add elements to priority queue (heap)
    valueList.forEach { priorityQueue.insert(it) }

    val resultList = mutableListOf<Int>()
    // get element from queue and remove them
    for (i in 0..ELEMENTS_COUNT) {
        resultList.add(priorityQueue.remove())
    }

    println()
    println("Elements extracted from the priority queue:\n" +
            " ${resultList.showList()}"
    )
}

private fun sort(valueList: List<Int>) {
    val sortedArray = SortHeap(valueList.size).sort(valueList.toTypedArray())
    println("Sorted list:\n ${sortedArray.toList().showList()}")
}

private fun <T> List<T>.showList() = this.joinToString(separator = ", ")