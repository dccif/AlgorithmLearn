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

    fun myAtoi(input: String): Int {
        var sign = 1
        var result = 0
        var index = 0
        val n = input.length

        // Discard all spaces from the beginning of the input string.
        while (index < n && input[index] == ' ') {
            index++
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input[index] == '+') {
            sign = 1
            index++
        } else if (index < n && input[index] == '-') {
            sign = -1
            index++
        }

        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(input[index])) {
            val digit = input[index] - '0'

            // Check overflow and underflow conditions.
            if (result > Int.MAX_VALUE / 10 || result == Int.MAX_VALUE / 10 && digit > Int.MAX_VALUE % 10) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
            }

            // Append current digit to the result.
            result = 10 * result + digit
            index++
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return sign * result
    }

    fun isPalindrome(x: Int): Boolean {
        var x = x
        if (x < 0 || (x % 10 == 0 && x != 0)) return false
        var revertnumbe = 0
        while (x > revertnumbe) {
            revertnumbe = revertnumbe * 10 + x % 10
            x /= 10
        }

        return x == revertnumbe || x == revertnumbe / 10
    }

    fun isMatch(text: String, pattern: String): Boolean {
        if (pattern.isEmpty()) return text.isEmpty()
        val first_match = !text.isEmpty() && (pattern[0] == text[0] || pattern[0] == '.')
        return if (pattern.length >= 2 && pattern[1] == '*') {
            isMatch(text, pattern.substring(2)) || first_match && isMatch(text.substring(1), pattern)
        } else {
            first_match && isMatch(text.substring(1), pattern.substring(1))
        }
    }
}

fun main() {
    var a = "abacdfgdcaba"
    var b = "abacdgfdcaba"
    var n = -123124
    var test = longestPalindrome()

    println(test.longestPalindrome(a))
    println(test.convert(a, 3))
    println(test.reverse(n))
}