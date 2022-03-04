public class TreeNode
{
	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int val = 0, TreeNode left = null, TreeNode right = null)
	{
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
List<IList<int>> levels = new List<IList<int>>();

void helper(TreeNode node, int level)
{
	if (levels.Count == level)
	{
		levels.Add(new List<int>());
	}
	levels[level].Add(node.val);

	if (node.left != null) helper(node.left, level + 1);
	if (node.right != null) helper(node.right, level + 1);
}

IList<IList<int>> levelOrderBottom(TreeNode root)
{
	if (root == null) return levels;
	helper(root, 0);
	levels.Reverse();
	return levels;
}