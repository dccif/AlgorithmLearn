package simpleSort

import kotlin.math.pow

fun radixSort(arr: Array<Int>?) {
    if (arr == null || arr.size < 2) {
        return
    }
    radixSort(arr, 0, arr.size - 1, maxbits(arr))
}

fun maxbits(arr: Array<Int>): Int {
    var max = Int.MIN_VALUE
    for (i in arr.indices) {
        max = max.coerceAtLeast(arr[i])
    }
    var res = 0
    while (max != 0) {
        res++
        max /= 10
    }
    return res
}

fun radixSort(arr: Array<Int>, L: Int, R: Int, digit: Int) {
    val radix = 10
    var i = 0
    var j = 0
    val bucket = Array<Int>(R - L + 1) { 0 }
    for (d in 1..digit) {
        val count = Array<Int>(radix) { 0 }
        for (i in L..R) {
            j = getDigit(arr[i], d)
            count[j]++
        }
        for (i in 1 until radix) {
            count[i] = count[i] + count[i - 1]
        }
        for (i in R downTo L) {
            j = getDigit(arr[i], d)
            bucket[count[j] - 1] = arr[i]
            count[j]--
        }

        i = L
        j = 0
        while (i <= R) {
            arr[i] = bucket[j]
            i++;j++
        }
    }
}

fun getDigit(x: Int, d: Int): Int {
    return ((x / (10.0.pow((d - 1).toDouble())).toInt() % 10))
}


fun main() {
    val ar = arrayOf(123, 54, 63, 76, 0, 54, 10, 23)
    radixSort(ar)
    println(ar.contentToString())
}