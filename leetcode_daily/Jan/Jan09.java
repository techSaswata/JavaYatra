class Solution {
    private int maxDepth;
    private TreeNode result;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        maxDepth = -1;
        result = null;
        dfs(root, 0);
        return result;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        int left = dfs(node.left, depth + 1);
        int right = dfs(node.right, depth + 1);
        if (left == right && left >= maxDepth) {
            maxDepth = left;
            result = node;
        }
        return Math.max(left, right);
    }
}