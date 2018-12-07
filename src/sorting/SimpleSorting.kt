package sorting

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
 * sorts array by selection sort algorithm
 *
 * @param array which isn't sorted
 * @return sorted array
 */
fun selectSort(array: Array<Int>): Array<Int> {
    for (j in 0 until array.size - 1) {
        var min = j
        for (i in j + 1 until array.size) {
            if (array[j] > array[i]) min = i

            swap(array, j, min)
        }
    }

    println("Selection sort:")
    println(Arrays.toString(array))

    return array
}

/**
 * sorts array by insert sort algorithm
 *
 * @param array which isn't sorted
 * @return sorted array
 */
fun insertSort(array: Array<Int>): Array<Int> {
    for (j in 1 until array.size) {
        val temp = array[j]
        var i = j
        while (i > 0 && array[i - 1] > temp) {
            array[i] = array[i - 1]
            i--
        }

        array[i] = temp
    }

    println("Insert sort:")
    println(Arrays.toString(array))

    return array
}

private fun swap(ar: Array<Int>, i: Int, j: Int) {
    if (ar[i] > ar[j]) {
        val temp = ar[i]
        ar[i] = ar[j]
        ar[j] = temp
    }
}