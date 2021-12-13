package Greedy

import java.util.*
import kotlin.Comparator

class MyComparator : Comparator<String> {
    override fun compare(o1: String?, o2: String?): Int {
        return (o1 + o2).compareTo(o2 + o1)
    }
}

fun lowestString(strs: Array<String>): String {
    if (strs == null || strs.size == 0) {
        return ""
    }
    strs.sortWith(MyComparator())
    val res = StringJoiner("")
    for (i in strs.indices) {
        res.add(strs[i])
    }
    return res.toString()
}

fun main() {
    val strs1 = arrayOf("jibw", "ji", "jp", "bw", "jibw")
    println(lowestString(strs1))

    val strs2 = arrayOf("ba", "b")
    println(lowestString(strs2))
}