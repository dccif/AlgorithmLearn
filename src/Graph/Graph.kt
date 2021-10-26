package Graph

import java.util.*

class Edge<T>(
    val weight: T,
    val from: Node<T>?,
    val to: Node<T>?
)

class Node<T>(
    val value: T,
    var invalue: Int = 0,
    var outvalue: Int = 0,
    val nexts: ArrayList<Node<T>?> = arrayListOf(),
    val edges: ArrayList<Edge<T>?> = arrayListOf()
)

class Graph<T>(
    val nodes: HashMap<Int, Node<T>?> = hashMapOf(),
    val edges: HashSet<Edge<T>?> = hashSetOf()
)

fun creatGraph(matrix: Array<Array<Int>>): Graph<Int> {
    var graph = Graph<Int>()
    for (idx in matrix.indices) {
        var from = matrix[idx][0]
        var to = matrix[idx][1]
        var weight = matrix[idx][2]
        if (!graph.nodes.containsKey(from)) {
            graph.nodes.put(from, Node(from))
        }
        if (!graph.nodes.containsKey(to)) {
            graph.nodes.put(to, Node(to))
        }
        val fromNode = graph.nodes.get(from)
        val toNode = graph.nodes.get(to)
        val newEdge = Edge<Int>(weight, fromNode, toNode)
        fromNode?.nexts?.add(toNode)
        fromNode?.outvalue = fromNode?.outvalue?.plus(1)!!
        toNode?.invalue = toNode?.invalue?.plus(1)!!
        fromNode.edges.add(newEdge)
        graph.edges.add(newEdge)
    }
    return graph
}

fun <T> bfsGraph(node: Node<T>?) {
    if (node == null) {
        return
    }
    val queue: Queue<Node<T>> = LinkedList()
    val set: HashSet<Node<T>> = HashSet()
    queue.add(node)
    set.add(node)
    while (!queue.isEmpty()) {
        val cur = queue.poll()
        println(cur.value)
        for (nx in cur.nexts) {
            if (!set.contains(nx)) {
                set.add(nx!!)
                queue.add(nx)
            }
        }
    }
}