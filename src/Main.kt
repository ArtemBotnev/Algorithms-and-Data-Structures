fun main(args: Array<String>) {
    // source array
    val array = arrayOf(10, 123, 34, 205, 0, 75, 94, 75, -92, 345, 12, 73, 110, 542, 243, 315, -54)
    // apply bubble sort
    bubbleSort(array)
    // apply selection sort
    selectSort(array)
    // apply insert sort
    insertSort(array)
    // apply quick sort
    quickSort(array)
    // apply merge sort
    mergeSort(array)
}