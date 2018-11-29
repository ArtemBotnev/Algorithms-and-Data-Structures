package sorting

import java.util.*

/**
 * sorts array by quick sort algorithm
 *
 * @param array which isn't sorted
 * @return sorted array
 */
fun quickSort(array: Array<Int>): Array<Int> {
    /**
     * sorts array
     *
     * @param arr which isn't sorted
     * @param low lower border in array which should be sorted
     * @param high higher border in array which should be sorted
     */
    fun sort(arr: Array<Int>, low: Int, high: Int) {
        if (low >= high) return

        // determine pivot element of array, just using random
        val pivot = arr[Random().nextInt(high - low + 1) + low]

        var left = low
        var right = high

        while (left <= right) {
            // move toward pivot
            // check that elements on the left of pivot are smaller than pivot
            while (arr[left] < pivot) left++
            // check that elements on the right of pivot are bigger than pivot
            while (arr[right] > pivot) right--
            // if not, sorting.swap it
            if (left <= right) {
                swap(arr, left, right)
                left++
                right--
            }
        }

        // recursively do the same for array's parts until its size is 1
        sort(arr, low, right)
        sort(arr, left, high)
    }

    // sort array
    sort(array, 0, array.size - 1)

    println("Quick sort:")
    println(Arrays.toString(array))
    println()

    return array
}

/**
 * sorts array by quick sort algorithm with last right element as a pivot
 *
 * @param array which isn't sorted
 * @return sorted array
 */
fun rightEdgePivotQuickSort(array: Array<Int>): Array<Int> {

    /**
     * partitions array or sub array to two part whit elements less than pivot
     * and elements more than pivot
     *
     * @param left - edge array or sub array
     * @param right - edge array or sub array
     * @param pivot element
     * @return index of pivot's final position
     */
    fun partition(left: Int, right: Int, pivot: Int): Int {
        var leftPointer = left - 1
        var rightPointer = right

        while (true) {
            // search element more than pivot into left part
            while (array[++leftPointer] < pivot) {}
            // search element less than pivot into right part
            while (rightPointer > 0 && array[--rightPointer] > pivot) {}

            // pointers' intersection
            if (leftPointer >= rightPointer) {
                break
            } else {
                // replace elements
                swap(array, leftPointer, rightPointer)
            }
        }

        // replace last left element and pivot
        swap(array, leftPointer, right)

        return leftPointer
    }

    /**
     * sorts array
     *
     * @param left border in array which should be sorted
     * @param right border in array which should be sorted
     */
    fun sort(left: Int, right: Int) {
        if (right - left <= 0) return

        // last right element as a pivot
        val pivot = array[right]

        val part = partition(left, right, pivot)

        sort(left, part - 1)
        sort(part + 1, right)
    }

    sort(0, array.size - 1)

    println("Right edge pivot quick sort:")
    println(Arrays.toString(array))
    println()

    return array
}

/**
 * sorts array by merge sort algorithm
 *
 * @param array which isn't sorted
 * @return sorted array
 */
fun mergeSort(array: Array<Int>) {
    /**
     * merges two arrays
     *
     * @param ar1 first array
     * @param ar2 second array
     * @return array that is the result of merge ar1 and ar2
     */
    fun merge(ar1: Array<Int>, ar2: Array<Int>): Array<Int> {
        val resSize = ar1.size + ar2.size
        val arRes = Array(resSize) { 0 }
        var index1 = 0
        var index2 = 0

        for (i in 0..(resSize - 1)) {
            arRes[i] = when {
                index1 == ar1.size -> ar2[index2++]
                index2 == ar2.size -> ar1[index1++]
                else ->
                    if (ar1[index1] < ar2[index2]) ar1[index1++] else ar2[index2++]
            }
        }

        return arRes
    }

    /**
     * splits source array into left and right part of it
     * does the same recursively until size of parts 1
     *
     * @param arr source array
     * @return final result
     */
    fun sort(arr: Array<Int>): Array<Int> {
        if (arr.size < 2) return arr

        val middle = arr.size shr 1

        // create copies of left and right parts
        val ar1 = Arrays.copyOfRange(arr, 0, middle)
        val ar2 = Arrays.copyOfRange(arr, middle, arr.size)

        val a = sort(ar1)
        val b = sort(ar2)

        return merge(a, b)
    }

    println("Merge sort: ")
    println(Arrays.toString(sort(array)))
    println()
}

/**
 * sorts array by Shell sort algorithm
 *
 * @param array which isn't sorted
 * @return sorted array
 */
fun shellSort(array: Array<Int>): Array<Int> {
    val size = array.size
    var j: Int
    var temp: Int
    var gap = 1

    while (gap <= size / 3) gap = 3 * gap + 1

    while (gap > 0) {
        for (i in gap until size) {
            temp = array[i]
            j = i

            while (j > gap - 1 && array[j - gap] >= temp) {
                array[j] =  array[j - gap]
                j -= gap
            }

            array[j] = temp
        }

        gap = (gap - 1) / 3
    }

    println("Shell sort: ")
    println(Arrays.toString(array))

    return array
}

/**
 * just swaps two elements of array
 *
 * @param ar array
 * @param i index of first element
 * @param j index of second element
 */
private fun swap(ar: Array<Int>, i: Int, j: Int) {
    if (ar[i] > ar[j]) {
        val temp = ar[i]
        ar[i] = ar[j]
        ar[j] = temp
    }
}