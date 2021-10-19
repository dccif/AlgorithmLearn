package Problem

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class AddTwoNum {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var dumpHead = ListNode(0)
        var p = l1
        var q = l2
        var curr = dumpHead
        var carry = 0
        while (p != null || q != null) {
            val x = p?.`val` ?: 0
            val y = q?.`val` ?: 0
            var sum = carry + x + y
            carry = sum / 10
            curr.next = ListNode(sum % 10)
            curr = curr.next!!
            if (p != null) p = p.next
            if (q != null) q = q.next
        }
        if (carry > 0) {
            curr.next = ListNode(carry)
        }
        return dumpHead.next
    }

    fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
        val head = ListNode(0)
        var current = head
        var p: ListNode? = l1
        var q: ListNode? = l2
        var v1: Int
        var v2: Int
        var sum: Int
        var carry = 0

        while (p != null || q != null || carry != 0) {
            v1 = p?.`val` ?: 0
            v2 = q?.`val` ?: 0
            sum = (v1 + v2 + carry) % 10
            carry = (v1 + v2 + carry) / 10
            current.next = ListNode(sum)

            // Move to next ListNode
            p?.let { p = p!!.next }
            q?.let { q = q!!.next }
            current = current.next!!
        }
        return head.next
    }


}