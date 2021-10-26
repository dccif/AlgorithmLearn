package Graph

import java.util.*

class Edge<T>(
    val weight: Int,
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

fun <T> dfsGraph(node: Node<T>?) {
    if (node == null) {
        return
    }
    val stack: Stack<Node<T>> = Stack()
    val set: HashSet<Node<T>> = HashSet()
    stack.add(node)
    set.add(node)
    println(node.value)
    while (!stack.isEmpty()) {
        val cur = stack.pop()
        for (nxt in cur.nexts) {
            if (!set.contains(nxt)) {
                stack.push(cur)
                stack.push(nxt)
                set.add(nxt!!)
                println(nxt.value)
                break
            }
        }
    }
}

fun <T> sortedTopology(graph: Graph<T>): List<Node<T>?> {
    val inMap: HashMap<Node<T>?, Int> = hashMapOf()
    val zeroInQueue: Queue<Node<T>?> = LinkedList()
    for (node in graph.nodes.values) {
        inMap.put(node, node!!.invalue)
        if (node.invalue == 0) {
            zeroInQueue.add(node)
        }
    }

    val resultList = arrayListOf<Node<T>?>()
    while (!zeroInQueue.isEmpty()) {
        val cur = zeroInQueue.poll()
        resultList.add(cur)
        for (nxt in cur!!.nexts) {
            inMap.put(nxt, inMap.get(nxt)!!.minus(1))
            if (inMap.get(nxt) == 0) {
                zeroInQueue.add(nxt)
            }
        }
    }
    return resultList
}

fun <T> dijkstra1(head: Node<T>?): HashMap<Node<T>, Int> {
    val distanceMap: HashMap<Node<T>, Int> = hashMapOf()
    distanceMap.put(head!!, 0)
    val selectedNodes: HashSet<Node<T>> = hashSetOf()
    var minNode: Node<T>? = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes)
    while (minNode != null) {
        val distance: Int = distanceMap.get(minNode)!!
        for (edge in minNode.edges) {
            val toNode = edge!!.to
            if (!distanceMap.containsKey(toNode)) {
                distanceMap.put(toNode!!, distance.plus(edge.weight))
            }
            edge.to?.let { distanceMap.put(it, Math.min(distanceMap.get(toNode)!!, distance + edge.weight)) }
        }
        selectedNodes.add(minNode)
        minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes)
    }
    return distanceMap
}

fun <T> getMinDistanceAndUnselectedNode(
    distanceMap: AbstractMap<Node<T>, Int>,
    touchedNodes: HashSet<Node<T>>
): Node<T>? {
    var minNode: Node<T>? = null
    var minDistance = Int.MAX_VALUE
    for (entry in distanceMap.entries) {
        val node: Node<T>? = entry.key
        val distance = entry.value
        if (!touchedNodes.contains(node) && distance < minDistance) {
            minNode = node
            minDistance = distance
        }
    }
    return minNode
}
