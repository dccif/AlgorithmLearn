package simpleSort

fun generateRandomArray(maxSize: Int, maxValue: Int): Array<Int> {
    return Array<Int>(((maxSize + 1) * Math.random()).toInt()) {
        ((maxValue + 1) * Math.random()).toInt() - (maxValue * Math.random()).toInt()
    }
}

fun comparator(arr: Array<Int>) {
    arr.sort()
}

fun main() {
    val testTime = 500000
    val maxSize = 100
    val maxValue = 100
    var succeed = true

    for (i in 0 until testTime step 1) {
        val arr1 = generateRandomArray(maxSize, maxValue)
        val arr2 = arr1.copyOf()
        insertionSort(arr1)
        comparator(arr2)
        if (!(arr1 contentEquals arr2)) {
            succeed = false
            break
        }
    }

    println(if (succeed) "Nice" else "Something wrong")

    val arr3 = generateRandomArray(maxSize, maxValue)
    println(arr3.contentToString())
    insertionSort(arr3)
    println(arr3.contentToString())
}
