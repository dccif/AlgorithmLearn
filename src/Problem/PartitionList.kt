package Problem

import java.util.*
import javax.swing.tree.TreeNode


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

fun reverseBetween(head: ListNode?, m: Int, n: Int): ListNode? {

    // Empty list
    var head = head
    var m = m
    var n = n
    if (head == null) {
        return null
    }

    // Move the two pointers until they reach the proper starting point
    // in the list.
    var cur = head
    var prev: ListNode? = null
    while (m > 1) {
        prev = cur
        cur = cur!!.next
        m--
        n--
    }

    // The two pointers that will fix the final connections.
    val con = prev
    val tail = cur

    // Iteratively reverse the nodes until n becomes 0.
    var third: ListNode? = null
    while (n > 0) {
        third = cur!!.next
        cur.next = prev
        prev = cur
        cur = third
        n--
    }

    // Adjust the final connections as explained in the algorithm
    if (con != null) {
        con.next = prev
    } else {
        head = prev
    }
    tail!!.next = cur
    return head
}

internal class Solution {
    var n = 0
    var s: String? = null
    var segments = LinkedList<String>()
    var output = ArrayList<String>()
    fun valid(segment: String): Boolean {
        /*
    Check if the current segment is valid :
    1. less or equal to 255
    2. the first character could be '0'
    only if the segment is equal to '0'
    */
        val m = segment.length
        if (m > 3) return false
        return if (segment[0] != '0') Integer.valueOf(segment) <= 255 else m == 1
    }

    fun update_output(curr_pos: Int) {
        /*
    Append the current list of segments
    to the list of solutions
    */
        val segment = s!!.substring(curr_pos + 1, n)
        if (valid(segment)) {
            segments.add(segment)
            output.add(java.lang.String.join(".", segments))
            segments.removeLast()
        }
    }

    fun backtrack(prev_pos: Int, dots: Int) {
        /*
    prev_pos : the position of the previously placed dot
    dots : number of dots to place
    */
        // The current dot curr_pos could be placed
        // in a range from prev_pos + 1 to prev_pos + 4.
        // The dot couldn't be placed
        // after the last character in the string.
        val max_pos = Math.min(n - 1, prev_pos + 4)
        for (curr_pos in prev_pos + 1 until max_pos) {
            val segment = s!!.substring(prev_pos + 1, curr_pos + 1)
            if (valid(segment)) {
                segments.add(segment) // place dot
                if (dots - 1 == 0) // if all 3 dots are placed
                    update_output(curr_pos) // add the solution to output
                else backtrack(curr_pos, dots - 1) // continue to place dots
                segments.removeLast() // remove the last placed dot
            }
        }
    }

    fun restoreIpAddresses(s: String): List<String> {
        n = s.length
        this.s = s
        backtrack(-1, 3)
        return output
    }
}

fun inorderTraversal(root: TreeNode?): List<Int>? {
    val res: MutableList<Int> = ArrayList()
    val stack = Stack<TreeNode>()
    var curr = root
    while (curr != null || !stack.isEmpty()) {
        while (curr != null) {
            stack.push(curr)
            curr = curr.left
        }
        curr = stack.pop()
        res.add(curr.`val`)
        curr = curr.right
    }
    return res
}

fun isInterleave(s1: String, s2: String, s3: String): Boolean {
    if (s3.length != s1.length + s2.length) {
        return false
    }
    val dp = BooleanArray(s2.length + 1)
    for (i in 0..s1.length) {
        for (j in 0..s2.length) {
            if (i == 0 && j == 0) {
                dp[j] = true
            } else if (i == 0) {
                dp[j] = dp[j - 1] && s2[j - 1] == s3[i + j - 1]
            } else if (j == 0) {
                dp[j] = dp[j] && s1[i - 1] == s3[i + j - 1]
            } else {
                dp[j] = dp[j] && s1[i - 1] == s3[i + j - 1] || dp[j - 1] && s2[j - 1] == s3[i + j - 1]
            }
        }
    }
    return dp[s2.length]
}

fun validate(root: TreeNode?, low: Int?, high: Int?): Boolean {
    // Empty trees are valid BSTs.
    if (root == null) {
        return true
    }
    // The current node's value must be between low and high.
    return if (low != null && root.`val` <= low || high != null && root.`val` >= high) {
        false
    } else validate(root.right, root.`val`, high) && validate(root.left, low, root.`val`)
    // The left and right subtree must also be valid.
}

fun isValidBST(root: TreeNode?): Boolean {
    return validate(root, null, null)
}