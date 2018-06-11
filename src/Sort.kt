import java.util.*

/**
 * sorts array by bubble sort algorithm
 *
 * @param array which isn't sorted
 * @return sorted array
 */
fun bubbleSort(array: Array<Int>): Array<Int> {
    for (j in array.size - 1 downTo 0)
        for (i in 0 until j)
            if (array[i] > array[i + 1])
                swap(array, i, i + 1)

    println("Bubble sort:")
    println(Arrays.toString(array))
    println()

    return array
}

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
            // if not, swap it
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