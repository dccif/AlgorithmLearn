package Greedy

fun win1(arr: IntArray): Int {
    if (arr == null || arr.size == 0) {
        return 0
    }
    return Math.max(f(arr, 0, arr.size - 1), s(arr, 0, arr.size - 1))
}

fun f(arr: IntArray, i: Int, j: Int): Int {
    if (i == j) {
        return arr[i]
    }
    return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1))
}

fun s(arr: IntArray, i: Int, j: Int): Int {
    if (i == j) {
        return 0
    }
    return Math.min(f(arr, i + 1, j), f(arr, i, j - 1))
}
