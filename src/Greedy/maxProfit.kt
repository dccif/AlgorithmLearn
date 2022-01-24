package Greedy

import java.util.*

class Node(val p: Int, val c: Int)

class maxProfit {
    class MinCostComparator : Comparator<Node> {
        override fun compare(o1: Node?, o2: Node?): Int {
            return o1?.c!! - o2!!.c
        }
    }

    class MaxProfitComparator : Comparator<Node> {
        override fun compare(o1: Node?, o2: Node?): Int {
            return o2?.p!! - o1!!.p
        }
    }

    fun findMaximizedCapital(k: Int, W: Int, Profits: IntArray, Capital: IntArray): Int {
        var W = W
        var minCostQ = PriorityQueue(MinCostComparator())
        var maxProfitQ = PriorityQueue(MaxProfitComparator())
        for (i in 0..Profits.size) {
            minCostQ.add(Node(Profits[i], Capital[i]))
        }
        for (i in 0..k) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll())
            }
            if (maxProfitQ.isEmpty()) {
                return W
            }
            W += maxProfitQ.poll().p
        }
        return W
    }
}