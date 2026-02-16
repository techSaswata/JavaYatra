import java.util.ArrayList;
import java.util.List;

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);
        return build(nodes, 0, nodes.size() - 1);
    }

    private void inorder(TreeNode node, List<TreeNode> nodes) {
        if (node == null) return;
        inorder(node.left, nodes);
        nodes.add(node);
        inorder(node.right, nodes);
    }

    private TreeNode build(List<TreeNode> nodes, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) >>> 1;
        TreeNode node = nodes.get(mid);
        node.left = build(nodes, start, mid - 1);
        node.right = build(nodes, mid + 1, end);
        return node;
    }
}