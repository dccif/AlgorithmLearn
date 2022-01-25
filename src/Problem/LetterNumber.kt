package Problem

import java.util.Map


private val combinations: MutableList<String> = ArrayList()
private val letters = Map.of(
    '2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
)
private var phoneDigits: String? = null

fun letterCombinations(digits: String): List<String>? {
    // If the input is empty, immediately return an empty answer array
    if (digits.length == 0) {
        return combinations
    }

    // Initiate backtracking with an empty path and starting index of 0
    phoneDigits = digits
    backtrack(0, StringBuilder())
    return combinations
}

private fun backtrack(index: Int, path: StringBuilder) {
    // If the path is the same length as digits, we have a complete combination
    if (path.length == phoneDigits!!.length) {
        combinations.add(path.toString())
        return  // Backtrack
    }

    // Get the letters that the current digit maps to, and loop through them
    val possibleLetters = letters[phoneDigits!![index]]
    for (letter in possibleLetters!!.toCharArray()) {
        // Add the letter to our current path
        path.append(letter)
        // Move on to the next digit
        backtrack(index + 1, path)
        // Backtrack by removing the letter before moving onto the next
        path.deleteCharAt(path.length - 1)
    }
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(0)
    dummy.next = head
    var first: ListNode? = dummy
    var second: ListNode? = dummy
    // Advances first pointer so that the gap between first and second is n nodes apart
    for (i in 1..n + 1) {
        first = first!!.next
    }
    // Move first to the end, maintaining the gap
    while (first != null) {
        first = first.next
        second = second!!.next
    }
    second!!.next = second.next!!.next
    return dummy.next
}

fun mergeTwoListsRe(l1: ListNode?, l2: ListNode?): ListNode? {
    return if (l1 == null) {
        l2
    } else if (l2 == null) {
        l1
    } else if (l1.`val` < l2.`val`) {
        l1.next = mergeTwoListsRe(l1.next, l2)
        l1
    } else {
        l2.next = mergeTwoListsRe(l1, l2.next)
        l2
    }
}

fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    // maintain an unchanging reference to node ahead of the return node.
    var l1 = l1
    var l2 = l2
    val prehead = ListNode(-1)
    var prev: ListNode? = prehead
    while (l1 != null && l2 != null) {
        if (l1.`val` <= l2.`val`) {
            prev!!.next = l1
            l1 = l1.next
        } else {
            prev!!.next = l2
            l2 = l2.next
        }
        prev = prev.next
    }

    // At least one of l1 and l2 can still have nodes at this point, so connect
    // the non-null list to the end of the merged list.
    prev!!.next = l1 ?: l2
    return prehead.next
}

fun generateParenthesis(n: Int): List<String?> {
    val ans: MutableList<String?> = ArrayList()
    if (n == 0) {
        ans.add("")
    } else {
        for (c in 0 until n) for (left in generateParenthesis(c)) for (right in generateParenthesis(n - 1 - c)) ans.add(
            "($left)$right"
        )
    }
    return ans
}

fun main() {
    println(letterCombinations("23"))
    println(generateParenthesis(3))
}