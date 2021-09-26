class CopyListWithRandom {
    class Node(var data: Int) {
        var value = data
        var next: Node? = null
        var rand: Node? = null
    }

    fun copyListWithRand1(head: Node?): Node? {
        val map = HashMap<Node, Node>()
        var cur = head
        while (cur != null) {
            map.put(cur, Node(cur.value))
            cur = cur.next
        }
        cur = head
        while (cur != null) {
            map.get(cur)?.next = map.get(cur.next)
            map.get(cur)?.rand = map.get(cur.rand)
            cur = cur.next
        }
        return map.get(head)
    }

    fun copyListWithRand2(head: Node?): Node? {
        if (head == null) {
            return null
        }
        var cur: Node? = head
        var next: Node? = null
        while (cur != null) {
            next = cur.next
            cur.next = Node(cur.value)
            cur.next?.next = next
            cur = next
        }
        cur = head
        var curCopy: Node? = null
        while (cur != null) {
            next = cur.next?.next
            curCopy = cur.next
            curCopy?.rand = if (cur.rand != null) cur.rand?.next else null
            cur = next
        }
        val res = head.next
        cur = head
        while (cur != null) {
            next = cur.next?.next
            curCopy = cur.next
            cur.next = next
            curCopy?.next = next?.next
            cur = next
        }
        return res
    }
}