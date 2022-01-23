package Problem


class longestPalindrome {
    fun longestPalindrome(s: String?): String? {
        if (s == null || s.length < 1) return ""
        var start = 0
        var end = 0
        for (i in 0 until s.length) {
            val len1 = expandAroundCenter(s, i, i)
            val len2 = expandAroundCenter(s, i, i + 1)
            val len = Math.max(len1, len2)
            if (len > end - start) {
                start = i - (len - 1) / 2
                end = i + len / 2
            }
        }
        return s.substring(start, end + 1)
    }

    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        var L = left
        var R = right
        while (L >= 0 && R < s.length && s[L] == s[R]) {
            L--
            R++
        }
        return R - L - 1
    }
    fun convert(s: String, numRows: Int): String? {
        if (numRows == 1) return s
        val rows: MutableList<StringBuilder> = ArrayList()
        for (i in 0 until Math.min(numRows, s.length)) rows.add(StringBuilder())
        var curRow = 0
        var goingDown = false
        for (c in s.toCharArray()) {
            rows[curRow].append(c)
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown
            curRow += if (goingDown) 1 else -1
        }
        val ret = StringBuilder()
        for (row in rows) ret.append(row)
        return ret.toString()
    }

    fun reverse(x: Int): Int {
        var x = x
        var rev = 0
        while (x != 0) {
            val pop = x % 10
            x /= 10
            if (rev > Int.MAX_VALUE / 10 || rev == Int.MAX_VALUE / 10 && pop > 7) return 0
            if (rev < Int.MIN_VALUE / 10 || rev == Int.MIN_VALUE / 10 && pop < -8) return 0
            rev = rev * 10 + pop
        }
        return rev
    }
}

fun main() {
    var a = "abacdfgdcaba"
    var b = "abacdgfdcaba"
    var n = -123124
    var test = longestPalindrome()

    println(test.longestPalindrome(a))
    println(test.convert(a,3))
    println(test.reverse(n))
}