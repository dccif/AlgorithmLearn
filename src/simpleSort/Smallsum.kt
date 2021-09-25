package simpleSort

fun smallSum(arr: Array<Int>?): Int {
    if (arr == null || arr.size < 2) {
        return 0
    }
    return processRec3(arr, 0, arr.size - 1)
}

fun processRec3(arr: Array<Int>, l: Int, r: Int): Int {
    if (l == r) {
        return 0
    }
    val mid = l + ((r - l) shr 1)
    return processRec3(arr, l, mid) + processRec3(arr, mid + 1, r) + mergeSum(arr, l, mid, r)
}

fun mergeSum(arr: Array<Int>, L: Int, m: Int, r: Int): Int {
    val help = Array<Int>(r - L + 1) { 0 }
    var i = 0
    var p1 = L
    var p2 = m + 1
    var res = 0
    while (p1 <= m && p2 <= r) {
        res += if (arr[p1] < arr[p2]) (r - p2 + 1) * arr[p1] else 0
        help[i++] = if (arr[p1] < arr[p2]) arr[p1++] else arr[p2++]
    }
    while (p1 <= m) {
        help[i++] = arr[p1++]
    }
    while (p2 <= r) {
        help[i++] = arr[p2++]
    }
    for (i in help.indices step 1) {
        arr[L + i] = help[i]
    }
    return res
}

fun main() {
    val ar = arrayOf(1, 3, 4, 2, 5)
    println(smallSum(ar))
}
