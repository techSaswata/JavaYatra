class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int val) {
        val = (val << 1) | node.val;
        if (node.left == null && node.right == null) {
            return val;
        }
        int sum = 0;
        if (node.left != null) {
            sum += dfs(node.left, val);
        }
        if (node.right != null) {
            sum += dfs(node.right, val);
        }
        return sum;
    }
}