package heaps

import java.lang.StringBuilder
import kotlin.math.log2

/**
 * Model of heap
 *
 * Integer for keys used to simplify
 */
open class Heap(private val maxSize: Int) : PriorityQueue<Int> {

    protected val heapArray = Array (maxSize) { 0 }
    protected var size = 0

    override fun insert(value: Int) {
        if (isFull()) throw ArrayIndexOutOfBoundsException(
                "Attempt to insert element into overloaded heap. Max size of heap = $size"
        )

        heapArray[size] = value
        moveUp(size++)
    }

    override fun remove(): Int {
        val root = heapArray[0]
        heapArray[0] = heapArray[--size]
        moveDown(0)

        return root
    }

    override fun peek() = heapArray[0]

    override fun isFull() = size == maxSize

    override fun isEmpty() = size == 0

    override fun toString() = showHeapAsString()

    override fun size() = size

    /**
     * moves element down the heap until it value less than value
     * its bigger child
     *
     * @param index - of element which should be moved
     */
    protected fun moveDown(index: Int) {
        val top = heapArray[index]
        var currentIndex = index
        var biggerChildIndex: Int

        while (currentIndex < size shr 1) {
            val leftChildIndex = (currentIndex shl 1) + 1
            val leftChild = heapArray[leftChildIndex]
            val rightChild = heapArray[leftChildIndex + 1]

            biggerChildIndex = if (leftChild > rightChild) {
                leftChildIndex
            } else {
                leftChildIndex + 1
            }

            if (heapArray[biggerChildIndex] <= top) break

            heapArray[currentIndex] = heapArray[biggerChildIndex]
            currentIndex = biggerChildIndex
        }

        heapArray[currentIndex] = top
    }

    /**
     * moves element up the heap until it value more than value its parent
     *
     * @param index - of element which should be moved
     */
    private fun moveUp(index: Int) {
        val bottom = heapArray[index]
        var currentIndex = index
        var parentIndex = (index - 1) shr 1

        while (currentIndex > 0
                && heapArray[parentIndex] < bottom) {

            heapArray[currentIndex] = heapArray[parentIndex]
            currentIndex = parentIndex
            parentIndex = (currentIndex - 1) shr 1
        }

        heapArray[currentIndex] = bottom
    }

    private fun showHeapAsString(): String {
        val builder = StringBuilder()
        var span = getMaxLevel() * getMaxLevel()

        for (i in 0 until size) {
            if ((i + 1).isPowTwo()) {
                builder.append('\n')
                span /= 2
            }

            for (i in 0..span) {
                builder.append(" ")
            }

            builder.append(heapArray[i])
        }

        return builder.toString()
    }

    /**
     * @return max level of heap
     */
    private fun getMaxLevel() = log2(size.toFloat()).toInt() + 1

    private fun Int.isPowTwo() = this > 0 && this and (this - 1) == 0
}