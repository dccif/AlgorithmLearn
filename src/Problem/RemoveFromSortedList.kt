package Problem

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
    while(current !=null && current.next !=null){
        if(current.next!!.`val` == current.`val`){
            current.next= current.next!!.next
        }else{
            current = current.next
        }
    }
    return head
}