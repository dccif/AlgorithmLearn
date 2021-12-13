package Trie

class TrieNode(var pass: Int = 0, var end: Int = 0, var nexts: Array<TrieNode?> = Array(26) { TrieNode() })

class Trie(var root: TrieNode = TrieNode()) {
    fun insert(word: String) {
        if (word == null) {
            return
        }
        val chs = word.toCharArray()
        var node = root
        node.pass++
        var index = 0
        for (i in chs.indices) {
            index = chs[i] - 'a'
            if (node.nexts[index] == null) {
                node.nexts[index] = TrieNode()
            }
            node = node.nexts[index]!!
            node.pass++
        }
        node.end++
    }

    fun delete(word: String) {
        if (search(word) != 0) {
            val chs = word.toCharArray()
            var node = root
            node.pass--
            var index = 0
            for (i in chs.indices) {
                index = chs[i] - 'a'
                if (--node.nexts[index]!!.pass == 0) {
                    node.nexts[index] = null
                    return
                }
                node = node.nexts[index]!!
            }
            node.end--
        }
    }

    fun search(word: String): Int {
        if (word == null) {
            return 0
        }
        val chs = word.toCharArray()
        var node = root
        var index = 0
        for (i in chs.indices) {
            index = chs[i] - 'a'
            if (node.nexts[index] == null) {
                return 0
            }
            node = node.nexts[index]!!
        }
        return node.end
    }

    fun prefixNumber(pre: String): Int {
        if (pre == null) {
            return 0
        }
        val chs = pre.toCharArray()
        var node = root
        var index = 0
        for (i in chs.indices) {
            index = chs[i] - 'a'
            if (node.nexts[index] == null) {
                return 0
            }
            node = node.nexts[index]!!
        }
        return node.pass
    }
}