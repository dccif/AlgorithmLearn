package SimpleSort

fun insertionSort(arr: Array<Int>?) {
    if (arr == null || arr.size < 2) {
        return
    }
    var j = 0
    for (i in 1 until arr.size step 1) {
        j = i - 1
        while (j >= 0 && arr[j] > arr[j + 1]) {
            xorswap(arr, j, j + 1)
            j--
        }
    }
}