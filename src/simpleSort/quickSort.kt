package simpleSort

fun quickSort(arr: Array<Int>?) {
    if (arr == null || arr.size < 2) {
        return
    }
    quickSort(arr, 0, arr.size - 1)
}

fun quickSort(arr: Array<Int>, L: Int, R: Int) {
    if (L < R) {
        swap(arr, L + (Math.random() * (R - L + 1)).toInt(), R)
        var p = partition(arr, L, R)
        quickSort(arr, L, p[0] - 1)
        quickSort(arr, p[1] + 1, R)
    }
}

fun partition(arr: Array<Int>, L: Int, R: Int): Array<Int> {
    var less = L - 1
    var L = L
    var more = R
    while (L < more) {
        if (arr[L] < arr[R]) {
            swap(arr, ++less, L++)
        } else if (arr[L] > arr[R]) {
            swap(arr, --more, L)
        } else {
            L++
        }
    }
    swap(arr, more, R)
    return arrayOf(less + 1, more)
}
