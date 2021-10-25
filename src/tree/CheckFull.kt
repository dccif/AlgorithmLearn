package tree

import kotlin.math.abs

data class ReturnType(val isBalanced: Boolean, val height: Int)

fun <T> process(x: Node<T>?): ReturnType {
    if (x == null) {
        return ReturnType(true, 0)
    }
    val leftData = process(x.left)
    val rightData = process(x.right)
    val height = leftData.height.coerceAtLeast(rightData.height) + 1
    val isBalanced = leftData.isBalanced && rightData.isBalanced && abs(leftData.height - rightData.height) < 2
    return ReturnType(isBalanced, height)
}

fun <T> isBalanced(head: Node<T>?): Boolean {
    return process(head).isBalanced
}

fun <T> lowestAncestor(head: Node<T>?, o1: Node<T>?, o2: Node<T>?): Node<T>? {
    if (head == null || head == o1 || head == o2) {
        return head
    }
    val left = lowestAncestor(head.left, o1, o2)
    val right = lowestAncestor(head.right, o1, o2)
    if (left != null && right != null) {
        return head
    }
    return left ?: right
}