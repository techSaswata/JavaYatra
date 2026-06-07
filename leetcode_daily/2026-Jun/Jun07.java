class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        TreeNode[] nodes = new TreeNode[100001];
        boolean[] isChild = new boolean[100001];
        
        for (int[] desc : descriptions) {
            if (nodes[desc[0]] == null) {
                nodes[desc[0]] = new TreeNode(desc[0]);
            }
            if (nodes[desc[1]] == null) {
                nodes[desc[1]] = new TreeNode(desc[1]);
            }
            
            if (desc[2] == 1) {
                nodes[desc[0]].left = nodes[desc[1]];
            } else {
                nodes[desc[0]].right = nodes[desc[1]];
            }
            
            isChild[desc[1]] = true;
        }
        
        for (int[] desc : descriptions) {
            if (!isChild[desc[0]]) {
                return nodes[desc[0]];
            }
        }
        
        return null;
    }
}