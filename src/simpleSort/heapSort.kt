package simpleSort

fun heapSort(arr: Array<Int>?) {
    if (arr == null || arr.size < 2) {
        return
    }
//    for (i in arr.indices) {
//        heapInsert(arr, i)
//    }
    for (i in arr.size - 1 downTo 0) {
        heapify(arr, i, arr.size)
    }

    var heapSize = arr.size
    swap(arr, 0, --heapSize)
    while (heapSize > 0) {
        heapify(arr, 0, heapSize)
        swap(arr, 0, --heapSize)
    }
}

fun heapify(arr: Array<Int>, i: Int, heapSize: Int) {
    var i = i
    var left = i * 2 + 1
    while (left < heapSize) {
        var largest = if (left + 1 < heapSize && arr[left + 1] > arr[left]) left + 1 else left
        largest = if (arr[largest] > arr[i]) largest else i
        if (largest == i) {
            break
        }
        swap(arr, largest, i)
        i = largest
        left = i * 2 + 1
    }
}

fun heapInsert(arr: Array<Int>, index: Int) {
    var index = index
    while (arr[index] > arr[(index - 1) / 2]) {
        swap(arr, index, (index - 1) / 2)
        index = (index - 1) / 2
    }
}
