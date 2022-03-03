package Problem

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class LevelOrderTraversal {


    var levels: MutableList<MutableList<Int>> = ArrayList()

    fun helper(node: TreeNode?, level: Int) {
        // start the current level
        if (levels.size == level) levels.add(ArrayList())

        // fulfil the current level
        levels[level].add(node!!.`val`)

        // process child nodes for the next level
        if (node.left != null) helper(node.left, level + 1)
        if (node.right != null) helper(node.right, level + 1)
    }

    fun levelOrder(root: TreeNode?): List<MutableList<Int>>? {
        if (root == null) return levels
        helper(root, 0)
        return levels
    }
}

class ConstructTree{
    var preorderIndex = 0
    var inorderIndexMap: MutableMap<Int, Int>? = null
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        preorderIndex = 0
        // build a hashmap to store value -> its index relations
        inorderIndexMap = HashMap()
        for (i in inorder.indices) {
            (inorderIndexMap as HashMap<Int, Int>)[inorder[i]] = i
        }
        return arrayToTree(preorder, 0, preorder.size - 1)
    }

    private fun arrayToTree(preorder: IntArray, left: Int, right: Int): TreeNode? {
        // if there are no elements to construct the tree
        if (left > right) return null

        // select the preorder_index element as the root and increment it
        val rootValue = preorder[preorderIndex++]
        val root = TreeNode(rootValue)

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap!![rootValue]!! - 1)
        root.right = arrayToTree(preorder, inorderIndexMap!![rootValue]!! + 1, right)
        return root
    }
}