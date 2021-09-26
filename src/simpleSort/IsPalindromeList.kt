package simpleSort

import java.util.*

class IsPalindromeList {

    class Node(var data: Int) {
        var value = data
        var next: Node? = null
    }

    fun isPalindrome1(head: Node?): Boolean {
        val stack = Stack<Node?>()
        var head = head
        var cur = head
        while (cur != null) {
            stack.push(cur)
            cur = cur.next
        }
        while (head != null) {
            if (head.value != stack.pop()?.value) {
                return false
            }
            head = head.next
        }
        return true
    }

    fun isPalindrome2(head: Node?): Boolean {
        var head = head
        if (head?.next == null) {
            return true
        }
        var right = head.next
        var cur = head
        while (cur?.next != null && cur.next?.next != null) {
            right = right?.next
            cur = cur.next?.next
        }
        val stack = Stack<Node?>()
        while (right != null) {
            stack.push(right)
            right = right.next
        }
        while (!stack.isEmpty()) {
            if (head?.value != stack.pop()?.value) {
                return false
            }
            head = head?.next
        }
        return true
    }

    fun isPalindrome3(head: Node?): Boolean {
        if (head?.next == null) {
            return true
        }
        var n1 = head
        var n2 = head
        while (n2?.next != null && n2.next?.next != null) {
            n1 = n1?.next
            n2 = n2.next?.next
        }
        n2 = n1?.next
        n1?.next = null
        var n3: Node? = null
        while (n2 != null) {
            n3 = n2.next
            n2.next = n1
            n1 = n2
            n2 = n3
        }
        n3 = n1
        n2 = head
        var res = true
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false
                break
            }
            n1 = n1.next
            n2 = n2.next
        }
        n1 = n3?.next
        n3?.next = null
        while (n1 != null) {
            n2 = n1.next
            n1.next = n3
            n3 = n1
            n1 = n2
        }
        return res
    }

    fun listPartition1(head: Node?, pivot: Int): Node? {
        if (head == null) {
            return head
        }
        var cur = head
        var i = 0
        while (cur != null) {
            i++
            cur = cur.next
        }
        val nodeArr = Array<Node?>(i) { Node(0) }
        i = 0
        cur = head
        while (i != nodeArr.size) {
            nodeArr[i] = cur
            cur = cur?.next
            i++
        }
        arrPartition(nodeArr, pivot)
        i = 1
        while (i != nodeArr.size) {
            nodeArr[i - 1]?.next = nodeArr[i]
            i++
        }
        nodeArr[i - 1]?.next = null
        return nodeArr[0]
    }

    fun arrPartition(nodeArr: Array<Node?>, pivot: Int) {
        var small = -1
        var big = nodeArr.size
        var index = 0
        while (index != big) {
            if (nodeArr[index]?.value!! < pivot) {
                swap(nodeArr, ++small, index++)
            } else if (nodeArr[index]?.value == pivot) {
                index++
            } else {
                swap(nodeArr, --big, index)
            }
        }
    }

    fun listPartition2(head: Node?, pivot: Int): Node? {
        var head = head
        var sH: Node? = null
        var sT: Node? = null
        var eH: Node? = null
        var eT: Node? = null
        var mH: Node? = null
        var mT: Node? = null
        var next: Node? = null
        while (head != null) {
            next = head.next
            head.next = null
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head
                    sT = head
                } else {
                    sT?.next = head
                    sT = head
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head
                    eT = head
                } else {
                    eT?.next = head
                    eT = head
                }
            } else {
                if (mH == null) {
                    mH = head
                    mT = head
                } else {
                    mT?.next = head
                    mT = head
                }
            }
            head = next
        }
        if (sT != null) {
            sT.next = eH
            eT = eT ?: sT
        }
        if (eT != null) {
            eT.next = mH
        }
        return sH ?: (eH ?: mH)
    }
}

fun main() {
    val m = IsPalindromeList()
    var ls1 = IsPalindromeList.Node(3)

    var ls2 = IsPalindromeList.Node(2)

    var ls3 = IsPalindromeList.Node(2)

    var ls4 = IsPalindromeList.Node(2)

    ls1.next = ls2
    ls2.next = ls3
    ls3.next = ls4
    ls4.next = null

    println(m.isPalindrome3(ls1))

}