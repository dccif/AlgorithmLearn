package Greedy

fun num1(n: Int): Int {
    if (n < 1) {
        return 0
    }
    val record = intArrayOf(n)
    return process1(0, record, n)
}

fun process1(i: Int, record: IntArray, n: Int): Int {
    if (i == n) {
        return 1
    }
    var res = 0
    for (j in 0..n) {
        if (isValid(record, i, j)) {
            record[i] = j
            res += process1(i + 1, record, n)
        }
    }
    return res
}

fun isValid(record: IntArray, i: Int, j: Int): Boolean {
    for (k in 0..i) {
        if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
            return false
        }
    }
    return true
}

fun num2(n: Int): Int {
    if (n < 1 || n > 32) {
        return 0
    }
    val limit = if (n == 32) -1 else (1 shl n) - 1
    return process2(limit, 0, 0, 0)
}

fun process2(limit: Int, colLim: Int, leftDiaLim: Int, rightDiaLim: Int): Int {
    if (colLim == limit) return 1
    var pos = 0
    var mostRightOne = 0
    pos = limit and ((colLim or leftDiaLim or rightDiaLim).inv())
    var res = 0
    while (pos != 0) {
        mostRightOne = pos and (pos.inv() + 1)
        pos = pos - mostRightOne
        res += process2(
            limit,
            colLim or mostRightOne,
            (leftDiaLim or mostRightOne) shl 1,
            (rightDiaLim or mostRightOne) ushr 1
        )
    }
    return res
}
