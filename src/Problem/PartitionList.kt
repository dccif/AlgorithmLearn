package Problem


fun partition(head: ListNode?, x: Int): ListNode? {

    // before and after are the two pointers used to create the two list
    // before_head and after_head are used to save the heads of the two lists.
    // All of these are initialized with the dummy nodes created.
    var head = head
    val before_head = ListNode(0)
    var before: ListNode? = before_head
    val after_head = ListNode(0)
    var after: ListNode? = after_head
    while (head != null) {

        // If the original list node is lesser than the given x,
        // assign it to the before list.
        if (head.`val` < x) {
            before!!.next = head
            before = before.next
        } else {
            // If the original list node is greater or equal to the given x,
            // assign it to the after list.
            after!!.next = head
            after = after.next
        }

        // move ahead in the original list
        head = head.next
    }

    // Last node of "after" list would also be ending node of the reformed list
    after!!.next = null

    // Once all the nodes are correctly assigned to the two lists,
    // combine them to form a single list which would be returned.
    before!!.next = after_head.next
    return before_head.next
}

internal class Solution {
    fun isScramble(s1: String?, s2: String?): Boolean {
        if (s1 == null || s2 == null) {
            return false
        }
        if (s1 == s2) {
            return true
        }
        val dp = Array(s1.length) {
            Array(s2.length) {
                arrayOfNulls<Boolean>(s1.length + 1)
            }
        }
        return dfs(s1, 0, s2, 0, s1.length, dp)
    }

    fun dfs(s1: String, start1: Int, s2: String, start2: Int, delta: Int, dp: Array<Array<Array<Boolean>>>): Boolean {
        if (delta < 1) {
            return false
        }
        if (dp[start1][start2][delta] != null) {
            return dp[start1][start2][delta]
        }
        if (delta == 1) {
            return s1[start1] == s2[start2]
        }
        val end1 = start1 + delta - 1
        val end2 = start2 + delta - 1
        var result = false
        for (i in 1 until delta) {
            //same parts equal
            val out1 = dfs(s1, start1, s2, start2, i, dp) && dfs(s1, start1 + i, s2, start2 + i, delta - i, dp)

            //reverse parts equal
            val out2 = dfs(s1, start1, s2, end2 - i + 1, i, dp) && dfs(s1, start1 + i, s2, start2, delta - i, dp)
            val out = out1 || out2
            if (out) {
                result = true
                break
            }
        }
        dp[start1][start2][delta] = result
        return result
    }
}

internal class Solution {
    var memo: MutableMap<Int, Int> = HashMap()
    fun numDecodings(s: String): Int {
        return recursiveWithMemo(0, s)
    }

    private fun recursiveWithMemo(index: Int, str: String): Int {
        // Have we already seen this substring?
        if (memo.containsKey(index)) {
            return memo[index]!!
        }

        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length) {
            return 1
        }

        // If the string starts with a zero, it can't be decoded
        if (str[index] == '0') {
            return 0
        }
        if (index == str.length - 1) {
            return 1
        }
        var ans = recursiveWithMemo(index + 1, str)
        if (str.substring(index, index + 2).toInt() <= 26) {
            ans += recursiveWithMemo(index + 2, str)
        }

        // Save for memoization
        memo[index] = ans
        return ans
    }
}