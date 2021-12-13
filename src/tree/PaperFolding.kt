package tree

fun printAllFolds(N: Int) {
    printProcess(1, N, true)
}

fun printProcess(i: Int, N: Int, down: Boolean) {
    if (i > N) {
        return
    }
    printProcess(i + 1, N, true)
    println(if (down) "ao" else "tu")
    printProcess(i + 1, N, false)
}

fun main() {
    val N = 3
    printAllFolds(N)
}

