package sorting

import java.util.*

fun main(args: Array<String>) {
    // source array
    val array = arrayOf(10, 123, 34, 205, 0, 75, 94, 75, -92, 345, 12, 73, 110, 542, 243, 315, -54)
    // apply bubble sort
    bubbleSort(copyFullArray(array))
    // apply selection sort
    selectSort(copyFullArray(array))
    // apply insert sort
    insertSort(copyFullArray(array))
    // apply quick sort
    quickSort(copyFullArray(array))
    // apply right edge pivot quick sort
    rightEdgePivotQuickSort(copyFullArray(array))
    // apply quick sort with median as a pivot
    medianQuickSort(copyFullArray(array))
    // apply merge sort
    mergeSort(copyFullArray(array))
    // apply Shell sort
    shellSort(copyFullArray(array))
}

private fun <T> copyFullArray(a: Array<T>) = Arrays.copyOf(a, a.size)