package simpleSort

import java.util.*

fun sortedArrDistanceLessK(arr: Array<Int>, k: Int) {
    val heap = PriorityQueue<Int>()
    var index = 0
    while (index < Math.min(arr.size, k)) {
        heap.add(arr[index])
        index++
    }
    var i = 0
    while (index < arr.size) {
        heap.add(arr[index])
        arr[i] = heap.poll()
        i++
        index++
    }
    while (!heap.isEmpty()) {
        arr[i++] = heap.poll()
    }
}