package simpleSort

fun selectionSort(arr: Array<Int>?) {
    if (arr == null || arr.size < 2) {
        return
    }
    for (i in 0 until arr.size - 1 step 1) {
        var minIndex = i
        for (j in i + 1 until arr.size step 1) {
            minIndex = if (arr[j] < arr[minIndex]) j else minIndex
        }
        swap(arr, i, minIndex)
    }
}

fun <T> swap(arr: Array<T>, i: Int, j: Int) {
    val tmp = arr[i]
    arr[i] = arr[j]
    arr[j] = tmp
}
