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

fun swapPairs1(head: ListNode?): ListNode? {

    // Dummy node acts as the prevNode for the head node
    // of the list and hence stores pointer to the head node.
    var head = head
    val dummy = ListNode(-1)
    dummy.next = head
    var prevNode = dummy
    while (head != null && head.next != null) {

        // Nodes to be swapped
        val firstNode: ListNode = head
        val secondNode = head.next

        // Swapping
        prevNode.next = secondNode
        firstNode.next = secondNode!!.next
        secondNode.next = firstNode

        // Reinitializing the head and prevNode for next swap
        prevNode = firstNode
        head = firstNode.next // jump
    }

    // Return the new head node.
    return dummy.next
}

fun swapPairs(head: ListNode?): ListNode? {

    // If the list has no node or has only one node left.
    if (head == null || head.next == null) {
        return head
    }

    // Nodes to be swapped
    val firstNode: ListNode = head
    val secondNode = head.next

    // Swapping
    firstNode.next = swapPairs(secondNode!!.next)
    secondNode.next = firstNode

    // Now the head is the second node
    return secondNode
}

fun removeDuplicates(nums: IntArray): Int {
    if (nums.size == 0) return 0
    var i = 0
    for (j in 1 until nums.size) {
        if (nums[j] != nums[i]) {
            i++
            nums[i] = nums[j]
        }
    }
    return i + 1
}

fun removeElement1(nums: IntArray, `val`: Int): Int {
    var i = 0
    for (j in nums.indices) {
        if (nums[j] != `val`) {
            nums[i] = nums[j]
            i++
        }
    }
    return i
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    var i = 0
    var n = nums.size
    while (i < n) {
        if (nums[i] == `val`) {
            nums[i] = nums[n - 1]
            // reduce array size by one
            n--
        } else {
            i++
        }
    }
    return n
}

fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0
    if (needle.length > haystack.length) return -1
    val needleIdx = IntArray(needle.length)
    var ln = 0
    var i = 1
    while (i < needle.length - 1) {
        if (needle[ln] == needle[i]) ln++ else {
            i -= ln
            ln = 0
            i++
            continue
        }
        needleIdx[i] = Math.max(needleIdx[i], ln)
        i++
    }
    var phay = 0
    var pneedle = 0
    while (phay < haystack.length && pneedle < needle.length) {
        if (haystack[phay] == needle[pneedle]) {
            pneedle++
            phay++
        } else if (pneedle > 0) {
            pneedle = needleIdx[pneedle - 1]
        } else phay++
    }
    return if (pneedle == needle.length) phay - pneedle else -1
}

private const val HALF_INT_MIN = -1073741824 // -2**30;


fun divide1(dividend: Int, divisor: Int): Int {

    // Special case: overflow.
    var dividend = dividend
    var divisor = divisor
    if (dividend == Int.MIN_VALUE && divisor == -1) {
        return Int.MAX_VALUE
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    var negatives = 2
    if (dividend > 0) {
        negatives--
        dividend = -dividend
    }
    if (divisor > 0) {
        negatives--
        divisor = -divisor
    }
    val doubles = ArrayList<Int>()
    val powersOfTwo = ArrayList<Int>()

    /* Nothing too exciting here, we're just making a list of doubles of 1 and
     * the divisor. This is pretty much the same as Approach 2, except we're
     * actually storing the values this time. */
    var powerOfTwo = -1
    while (divisor >= dividend) {
        doubles.add(divisor)
        powersOfTwo.add(powerOfTwo)
        // Prevent needless overflows from occurring...
        if (divisor < HALF_INT_MIN) {
            break
        }
        divisor += divisor
        powerOfTwo += powerOfTwo
    }
    var quotient = 0
    /* Go from largest double to smallest, checking if the current double fits.
     * into the remainder of the dividend. */for (i in doubles.indices.reversed()) {
        if (doubles[i] >= dividend) {
            // If it does fit, add the current powerOfTwo to the quotient.
            quotient += powersOfTwo[i]
            // Update dividend to take into account the bit we've now removed.
            dividend -= doubles[i]
        }
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */return if (negatives != 1) {
        -quotient
    } else quotient
}

fun divideWithBit(dividend: Int, divisor: Int): Int {

    // Special case: overflow.
    var dividend = dividend
    var divisor = divisor
    if (dividend == Int.MIN_VALUE && divisor == -1) {
        return Int.MAX_VALUE
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    var negatives = 2
    if (dividend > 0) {
        negatives--
        dividend = -dividend
    }
    if (divisor > 0) {
        negatives--
        divisor = -divisor
    }

    /* In the first loop, we simply find the largest double of divisor
     * that fits into the dividend.
     * The >= is because we're working in negatives. In essence, that
     * piece of code is checking that we're still nearer to 0 than we
     * are to INT_MIN. */
    var highestDouble = divisor
    var highestPowerOfTwo = -1
    while (highestDouble >= HALF_INT_MIN && dividend <= highestDouble + highestDouble) {
        highestPowerOfTwo += highestPowerOfTwo
        highestDouble += highestDouble
    }

    /* In the second loop, we work out which powers of two fit in, by
     * halving highestDouble and highestPowerOfTwo repeatedly.
     * We can do this using bit shifting so that we don't break the
     * rules of the question :-) */
    var quotient = 0
    while (dividend <= divisor) {
        if (dividend <= highestDouble) {
            quotient += highestPowerOfTwo
            dividend -= highestDouble
        }
        /* We know that these are always even, so no need to worry about the
         * annoying "bit-shift-odd-negative-number" case. */highestPowerOfTwo = highestPowerOfTwo shr 1
        highestDouble = highestDouble shr 1
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */return if (negatives != 1) {
        -quotient
    } else quotient
}

fun divide(dividend: Int, divisor: Int): Int {

    // Special cases: overflow.
    var dividend = dividend
    var divisor = divisor
    if (dividend == Int.MIN_VALUE && divisor == -1) {
        return Int.MAX_VALUE
    }
    if (dividend == Int.MIN_VALUE && divisor == 1) {
        return Int.MIN_VALUE
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    var negatives = 2
    if (dividend > 0) {
        negatives--
        dividend = -dividend
    }
    if (divisor > 0) {
        negatives--
        divisor = -divisor
    }

    /* We want to find the largest doubling of the divisor in the negative 32-bit
     * integer range that could fit into the dividend.
     * Note if it would cause an overflow by being less than HALF_INT_MIN,
     * then we just stop as we know double it would not fit into INT_MIN anyway. */
    var maxBit = 0
    while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
        maxBit += 1
        divisor += divisor
    }
    var quotient = 0
    /* We start from the biggest bit and shift our divisor to the right
     * until we can't shift it any further */for (bit in maxBit downTo 0) {
        /* If the divisor fits into the dividend, then we should set the current
         * bit to 1. We can do this by subtracting a 1 shifted by the appropriate
         * number of bits. */
        if (divisor >= dividend) {
            quotient -= 1 shl bit
            /* Remove the current divisor from the dividend, as we've now
             * considered this part. */dividend -= divisor
        }
        /* Shift the divisor to the right so that it's in the right place
         * for the next positon we're checking at. */divisor = divisor + 1 shr 1
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */if (negatives != 1) {
        quotient = -quotient
    }
    return quotient
}

fun main() {
    println(letterCombinations("23"))
    println(generateParenthesis(3))
    println(strStr("helloe", "llo"))
}