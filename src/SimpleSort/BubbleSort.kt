package SimpleSort

fun bubbleSort(arr: Array<Int>?) {
    if (arr == null || arr.size < 2) {
        return
    }
    for (e in arr.size - 1 until 0 step -1) {
        for (i in 0 until e step 1) {
            if (arr[i] > arr[i + 1]) {
                xorswap(arr, i, i + 1)
            }
        }
    }
}

fun xorswap(arr: Array<Int>, i: Int, j: Int) {
    arr[i] = arr[i] xor arr[j]
    arr[j] = arr[i] xor arr[j]
    arr[i] = arr[i] xor arr[j]
}