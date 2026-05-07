class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] parent = new int[n];
        int[] max_val = new int[n];
        int[] rank = new int[n];
        int[] stack = new int[n];
        int top = -1;
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            max_val[i] = nums[i];
            
            int cur = i;
            while (top >= 0 && max_val[stack[top]] > nums[i]) {
                int popped = stack[top--];
                if (rank[cur] < rank[popped]) {
                    parent[cur] = popped;
                    if (max_val[cur] > max_val[popped]) {
                        max_val[popped] = max_val[cur];
                    }
                    cur = popped;
                } else if (rank[cur] > rank[popped]) {
                    parent[popped] = cur;
                    if (max_val[popped] > max_val[cur]) {
                        max_val[cur] = max_val[popped];
                    }
                } else {
                    parent[cur] = popped;
                    if (max_val[cur] > max_val[popped]) {
                        max_val[popped] = max_val[cur];
                    }
                    rank[popped]++;
                    cur = popped;
                }
            }
            stack[++top] = cur;
        }
        
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int root = i;
            while (root != parent[root]) {
                root = parent[root];
            }
            int curr = i;
            while (curr != root) {
                int nxt = parent[curr];
                parent[curr] = root;
                curr = nxt;
            }
            ans[i] = max_val[root];
        }
        
        return ans;
    }
}