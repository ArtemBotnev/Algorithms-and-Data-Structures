package sorting

import heaps.SortHeap
import utils.Timer
import java.util.*
import kotlin.math.sign

fun main(args: Array<String>) {
    // source arrays
    val array = arrayOf(10, 123, 34, 205, 0, 75, 94, 75, -92, 345, 12, 73, 110, 542, 243, 315, -54)
    val bigArray = Array(5_000) { Random().nextInt(10_000) }

    // apply bubble sort
    sort(array) { bubbleSort(it) }
    sort(bigArray) { bubbleSort(it) }

    // apply selection sort
    sort(array) { selectSort(it) }
    sort(bigArray) { selectSort(it) }

    // apply insert sort
    sort(array) { insertSort(it) }
    sort(bigArray) { insertSort(it) }

    // apply quick sort
    sort(array) { quickSort(it) }
    sort(bigArray) { quickSort(it) }

    // apply right edge pivot quick sort
    sort(array) { rightEdgePivotQuickSort(it) }
    sort(bigArray) { rightEdgePivotQuickSort(it) }

    // apply quick sort with median as a pivot
    sort(array) { medianQuickSort(it) }
    sort(bigArray) { medianQuickSort(it) }

    // apply merge sort
    sort(array) { mergeSort(it) }
    sort(bigArray) { mergeSort(it) }

    // apply Shell sort
    sort(array) { shellSort(it) }
    sort(bigArray) { shellSort(it) }

    // apply Heap sort
    println("Heap sort:")
    sort(array) { SortHeap(it.size).sort(it) }
    println("Heap sort:")
    sort(bigArray) { SortHeap(it.size).sort(it) }
}

private inline fun <T> sort(arr: Array<T>, sortFin: (Array<T>) -> Array<T>) {
    val timer = Timer()

    val arrCopy = Arrays.copyOf(arr, arr.size)
    timer.start()
    sortFin(arrCopy)

    val arraySize = if (arrCopy.size < 1_000) "small" else "big"
    print("For $arraySize array ")
    println(timer.stopAndShowTime())
    println()
}