package simpleSort

class Node(var data: Int) {
    var value = data
    var next: Node? = null
}

fun getLoopNode(head: Node?): Node? {
    if (head == null || head.next == null || head.next?.next == null) {
        return null
    }
    var n1 = head.next
    var n2 = head.next?.next
    while (n1 != n2) {
        if (n2?.next == null || n2.next?.next == null) {
            return null
        }
        n2 = n2.next?.next
        n1 = n1?.next
    }
    n2 = head
    while (n1 != n2) {
        n1 = n1?.next
        n2 = n2?.next
    }
    return n1
}

fun noLoop(head1: Node?, head2: Node?): Node? {
    if (head1 == null || head2 == null) {
        return null
    }
    var cur1 = head1
    var cur2 = head2
    var n = 0
    while (cur1?.next != null) {
        n++
        cur1 = cur1.next
    }
    while (cur2?.next != null) {
        n--
        cur2 = cur2.next
    }
    if (cur1 != cur2) {
        return null
    }
    cur1 = if (n > 0) head1 else head2
    cur2 = if (cur1 == head1) head2 else head1
    n = Math.abs(n)
    while (n != 0) {
        n--
        cur1 = cur1?.next
    }
    while (cur1 != cur2) {
        cur1 = cur1?.next
        cur2 = cur2?.next
    }
    return cur1
}

fun bothLoop(head1: Node?, loop1: Node?, head2: Node?, loop2: Node?): Node? {
    var cur1: Node? = null
    var cur2: Node? = null
    if (loop1 == loop2) {
        cur1 = head1
        cur2 = head2
        var n = 0
        while (cur1 != loop1) {
            n++
            cur1 = cur1?.next
        }
        while (cur2 != loop2) {
            n--
            cur2 = cur2?.next
        }
        cur1 = if (n > 0) head1 else head2
        cur2 = if (cur1 == head1) head2 else head1
        n = Math.abs(n)
        while (n != 0) {
            n--
            cur1 = cur1?.next
        }
        while (cur1 != cur2) {
            cur1 = cur1?.next
            cur2 = cur2?.next
        }
        return cur1
    } else {
        cur1 = loop1?.next
        while (cur1 != loop1) {
            if (cur1 == loop2) {
                return loop1
            }
            cur1 = cur1?.next
        }
        return null
    }
}

fun getIntersectNode(head1: Node?, head2: Node?): Node? {
    if (head1 == null || head2 == null) {
        return null
    }
    val loop1 = getLoopNode(head1)
    val loop2 = getLoopNode(head2)
    if (loop1 == null && loop2 == null) {
        return noLoop(head1, head2)
    }
    if (loop1 != null && loop2 != null) {
        return bothLoop(head1, loop1, head2, loop2)
    }
    return null
}