package Problem

import java.util.*
import kotlin.collections.ArrayDeque


fun deleteDuplicates(head: ListNode?): ListNode? {
    // sentinel
    var head = head
    val sentinel = ListNode(0, head)

    // predecessor = the last node
    // before the sublist of duplicates
    var pred: ListNode? = sentinel
    while (head != null) {
        // if it's a beginning of duplicates sublist
        // skip all duplicates
        if (head.next != null && head.`val` == head.next!!.`val`) {
            // move till the end of duplicates sublist
            while (head!!.next != null && head.`val` == head.next!!.`val`) {
                head = head.next
            }
            // skip all duplicates
            pred!!.next = head.next
            // otherwise, move predecessor
        } else {
            pred = pred!!.next
        }

        // move forward
        head = head.next
    }
    return sentinel.next
}

fun deleteDuplicates2(head: ListNode?): ListNode? {
    var current = head
    while (current != null && current.next != null) {
        if (current.next!!.`val` == current.`val`) {
            current.next = current.next!!.next
        } else {
            current = current.next
        }
    }
    return head
}

fun largestRectangleArea1(heights: IntArray): Int {
    var maxarea = 0
    for (i in heights.indices) {
        for (j in i until heights.size) {
            var minheight = Int.MAX_VALUE
            for (k in i..j) minheight = Math.min(minheight, heights[k])
            maxarea = Math.max(maxarea, minheight * (j - i + 1))
        }
    }
    return maxarea
}

fun largestRectangleArea2(heights: IntArray): Int {
    var maxArea = 0
    val length = heights.size
    for (i in 0 until length) {
        var minHeight = Int.MAX_VALUE
        for (j in i until length) {
            minHeight = Math.min(minHeight, heights[j])
            maxArea = Math.max(maxArea, minHeight * (j - i + 1))
        }
    }
    return maxArea
}

fun calculateArea(heights: IntArray, start: Int, end: Int): Int {
    if (start > end) return 0
    var minindex = start
    for (i in start..end) if (heights[minindex] > heights[i]) minindex = i
    return Math.max(
        heights[minindex] * (end - start + 1),
        Math.max(
            calculateArea(heights, start, minindex - 1),
            calculateArea(heights, minindex + 1, end)
        )
    )
}

fun largestRectangleArea3(heights: IntArray): Int {
    return calculateArea(heights, 0, heights.size - 1)
}

fun largestRectangleArea(heights: IntArray): Int {
    val stack: Deque<Int> = ArrayDeque<Int>()
    stack.push(-1)
    val length = heights.size
    var maxArea = 0
    for (i in 0 until length) {
        while (stack.peek() != -1
            && heights[stack.peek()] >= heights[i]
        ) {
            val currentHeight = heights[stack.pop()]
            val currentWidth = i - stack.peek() - 1
            maxArea = Math.max(maxArea, currentHeight * currentWidth)
        }
        stack.push(i)
    }
    while (stack.peek() != -1) {
        val currentHeight = heights[stack.pop()]
        val currentWidth = length - stack.peek() - 1
        maxArea = Math.max(maxArea, currentHeight * currentWidth)
    }
    return maxArea
}