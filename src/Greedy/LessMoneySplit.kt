package Greedy

import java.util.*

fun lessMoney(arr: Array<Int>): Int {
    val pQ = PriorityQueue<Int>()
    for (i in arr.indices) {
        pQ.add(arr[i])
    }
    var sum = 0
    var cur = 0
    while (pQ.size > 1) {
        cur = pQ.poll() + pQ.poll()
        sum += cur
        pQ.add(cur)
    }
    return sum
}

class MinheapComparator : Comparator<Int> {
    override fun compare(o1: Int?, o2: Int?): Int {
        return o1!! - o2!!
    }
}