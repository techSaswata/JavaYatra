package leetcode_daily.Jan26;
import java.util.*;

class Solution {
    public int maxLevelSum(TreeNode root) {
        TreeNode[] queue = new TreeNode[10005];
        int head = 0;
        int tail = 0;
        
        queue[tail++] = root;
        
        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;
        int currentLevel = 1;
        
        while (head < tail) {
            int currentSum = 0;
            int limit = tail;
            
            while (head < limit) {
                TreeNode node = queue[head++];
                currentSum += node.val;
                if (node.left != null) queue[tail++] = node.left;
                if (node.right != null) queue[tail++] = node.right;
            }
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxLevel = currentLevel;
            }
            currentLevel++;
        }
        
        return maxLevel;
    }
}