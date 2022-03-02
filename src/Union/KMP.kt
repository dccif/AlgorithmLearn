package Union

fun getIndexOf(s: String, m: String): Int {
    if (s == null || m == null || m.length < 1 || s.length < m.length) {
        return -1
    }
    val str1 = s.toCharArray()
    val str2 = m.toCharArray()
    var i1 = 0
    var i2 = 0
    val next = getNextArray(str2)
    while (i1 < str1.size && i2 < str2.size) {
        if (str1[i1] == str2[i2]) {
            i1++
            i2++
        } else if (next[i2] == -1) {
            i1++
        } else {
            i2 = next[i2]
        }
    }
    return if (i2 == str2.size) i1 - i2 else -1
}

fun getNextArray(ms: CharArray): IntArray {
    if (ms.size == 1) {
        return intArrayOf(-1)
    }
    val next = IntArray(ms.size)
    next[0] = -1
    next[1] = 0
    var i = 2
    var cn = 0
    while (i < next.size) {
        if (ms[i - 1] == ms[cn]) {
            next[i++] = ++cn
        } else if (cn > 0) {
            cn = next[cn]
        } else {
            next[i++] = 0
        }
    }
    return next
}
