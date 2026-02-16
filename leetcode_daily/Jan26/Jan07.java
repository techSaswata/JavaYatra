package leetcode_daily.Jan26;
class Solution {
    private int[] treeSums = new int[50005];
    private int count = 0;

    public int maxProduct(TreeNode root) {
        count = 0;
        long totalSum = dfs(root);
        long maxProd = 0;
        for (int i = 0; i < count; i++) {
            long s = treeSums[i];
            long p = s * (totalSum - s);
            if (p > maxProd) {
                maxProd = p;
            }
        }
        return (int)(maxProd % 1000000007);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int s = node.val + dfs(node.left) + dfs(node.right);
        treeSums[count++] = s;
        return s;
    }
}