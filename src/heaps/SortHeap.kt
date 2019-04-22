package heaps

/**
 * Extension of heap specially for sorting
 */
class SortHeap(maxSize: Int) : Heap(maxSize + 1) {

    fun sort(array: Array<Int>): Array<Int> {
        array.forEachIndexed { index, value ->
            heapArray[index] = value
            size++
        }

        // converts array to heap
        for (i in (size shr 1) - 1 downTo 0) moveDown(i)

        val result = Array(array.size) { 0 }
        for (i in 0 until array.size) {
            result[i] = remove()
        }

        return result
    }
}