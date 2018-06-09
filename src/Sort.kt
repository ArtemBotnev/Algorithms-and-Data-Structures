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