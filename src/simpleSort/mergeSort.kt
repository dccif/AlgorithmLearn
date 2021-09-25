package simpleSort

fun mergeSort(arr: Array<Int>?) {
    if (arr == null || arr.size < 2) {
        return
    }
    processRec2(arr, 0, arr.size - 1)
}

fun processRec2(arr: Array<Int>, L: Int, R: Int) {
    if (L == R) {
        return
    }
    val mid = L + ((R - L) shr 1)
    processRec2(arr, L, mid)
    processRec2(arr, mid + 1, R)
    mergeTri(arr, L, mid, R)
}

fun mergeTri(arr: Array<Int>, L: Int, M: Int, R: Int) {
    val help = Array<Int>(R - L + 1) { 0 }
    var i = 0
    var p1 = L
    var p2 = M + 1
    while (p1 <= M && p2 <= R) {
        help[i++] = if (arr[p1] <= arr[p2]) arr[p1++] else arr[p2++]
    }
    while (p1 <= M) {
        help[i++] = arr[p1++]
    }
    while (p2 <= R) {
        help[i++] = arr[p2++]
    }
    for (i in help.indices step 1) {
        arr[L + i] = help[i]
    }
}
