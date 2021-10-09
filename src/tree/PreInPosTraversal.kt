package tree

import java.util.*

class Node<T>(data: T, val left: Node<T>?, val right: Node<T>?) {
    val value = data
}

fun <T> preOrderRecur(head: Node<T>?) {
    if (head == null) {
        return
    }
    print("${head.value} ")
    preOrderRecur(head.left)
    preOrderRecur(head.right)
}

fun <T> inOrderRecur(head: Node<T>?) {
    if (head == null) {
        return
    }
    inOrderRecur(head.left)
    print("${head.value} ")
    inOrderRecur(head.right)
}

fun <T> posOrderRecur(head: Node<T>?) {
    if (head == null) {
        return
    }
    posOrderRecur(head.left)
    posOrderRecur(head.right)
    print("${head.value} ")
}

fun <T> preOrderUnRecur(head: Node<T>?) {
    var head = head
    print("pre-order: ")
    if (head != null) {
        val myStack = Stack<Node<T>?>()
        myStack.add(head)
        while (!myStack.isEmpty()) {
            head = myStack.pop()
            print("${head?.value} ")
            if (head?.right != null) {
                myStack.push(head.right)
            }
            if (head?.left != null) {
                myStack.push(head.left)
            }
        }
    }
}

fun <T> posOrderUnRecur1(head: Node<T>?) {
    var head = head
    print("pos-order: ")
    if (head != null) {
        val s1 = Stack<Node<T>?>()
        val s2 = Stack<Node<T>?>()
        s1.push(head)
        while (!s1.isEmpty()) {
            head = s1.pop()
            s2.push(head)
            if (head?.left != null) {
                s1.push(head.left)
            }
            if (head?.right != null) {
                s1.push(head.right)
            }
        }
        while (!s2.isEmpty()) {
            print("${s2.pop()?.value} ")
        }
    }
}

fun <T> inOrderUnRecur(head: Node<T>?) {
    var head = head
    print("in-order: ")
    if (head != null) {
        val st = Stack<Node<T>?>()
        while (!st.isEmpty() || head != null) {
            if (head != null) {
                st.push(head)
                head = head.left
            } else {
                head = st.pop()
                print("${head?.value} ")
                head = head?.right
            }
        }
    }
}

fun <T> wTravel(head: Node<T>?) {
    if (head == null) {
        return
    }
    val que = LinkedList<Node<T>?>()
    que.add(head)
    while (!que.isEmpty()) {
        var cur = que.poll()
        print(cur?.value)
        if (cur?.left != null) {
            que.add(cur.left)
        }
        if (cur?.right != null) {
            que.add(cur.right)
        }
    }
}

fun <T> maxwTravel(head: Node<T>?) {
    if (head == null) {
        return
    }
    val que = LinkedList<Node<T>?>()
    que.add(head)
    val levelMap = HashMap<Node<T>?, Int>()
    levelMap.put(head, 1)
    var curLevel = 1
    var curLevelNodes = 0
    var maxNum = Int.MIN_VALUE
    while (!que.isEmpty()) {
        var cur = que.poll()
        var curNodeLevel = levelMap.get(cur)
        if (curNodeLevel == curLevel) {
            curLevelNodes++
        } else {
            maxNum = Math.max(maxNum, curLevelNodes)
            curLevel++
            curLevelNodes = 1
        }
        if (cur?.left != null) {
            levelMap.put(cur.left, curNodeLevel!! + 1)
            que.add(cur.left)
        }
        if (cur?.right != null) {
            levelMap.put(cur.right, curNodeLevel!! + 1)
            que.add(cur.right)
        }
    }
}