package simpleSort

fun getMax(arr: Array<Int>): Int {
    return processRec1(arr, 0, arr.size - 1)
}

/* Master 公式
 T(N) = a*T(N/b) + O(N^d)
 1) log(b,a) > d -> O(N^log(b,a))
 2) log(b,a) = d -> O(N^d * logN)
 3) log(b,a) < d -> O(N^d)
 */
fun processRec1(arr: Array<Int>, L: Int, R: Int): Int {
    if (L == R) {
        return arr[L]
    }
    val mid = L + ((R - L) shr 1)
    val leftMax = processRec1(arr, L, mid)
    val rightMax = processRec1(arr, mid + 1, R)
    return leftMax.coerceAtLeast(rightMax)
}

fun main() {
    val ar = arrayOf(3, 2, 5, 6, 7, 4)
    println(getMax(ar))
}