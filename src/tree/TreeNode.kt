package tree

class Node<T>(data: T, val left: Node<T>?, val right: Node<T>?) {
    val value = data
}