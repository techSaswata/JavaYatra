import java.util.Arrays;

class Solution {
    private int[] minVal;
    private int[] maxVal;
    private int[] lazy;
    private int n;

    public int longestBalanced(int[] nums) {
        n = nums.length;
        int size = 1;
        while (size <= n) size <<= 1;
        
        minVal = new int[size << 1];
        maxVal = new int[size << 1];
        lazy = new int[size << 1];
        
        int[] last = new int[100001];
        Arrays.fill(last, -1);
        
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            int val = (nums[i] & 1) == 0 ? 1 : -1;
            int prev = last[nums[i]];
            
            update(1, 0, n - 1, prev + 1, i, val);
            last[nums[i]] = i;
            
            int idx = query(1, 0, n - 1, i);
            if (idx != -1) {
                if (i - idx + 1 > maxLen) {
                    maxLen = i - idx + 1;
                }
            }
        }
        return maxLen;
    }

    private void push(int node) {
        if (lazy[node] != 0) {
            int left = node << 1;
            int right = left | 1;
            int lz = lazy[node];
            
            lazy[left] += lz;
            minVal[left] += lz;
            maxVal[left] += lz;
            
            lazy[right] += lz;
            minVal[right] += lz;
            maxVal[right] += lz;
            
            lazy[node] = 0;
        }
    }

    private void update(int node, int start, int end, int l, int r, int val) {
        if (l > end || r < start) return;
        if (l <= start && end <= r) {
            lazy[node] += val;
            minVal[node] += val;
            maxVal[node] += val;
            return;
        }
        push(node);
        int mid = (start + end) >> 1;
        update(node << 1, start, mid, l, r, val);
        update((node << 1) | 1, mid + 1, end, l, r, val);
        minVal[node] = Math.min(minVal[node << 1], minVal[(node << 1) | 1]);
        maxVal[node] = Math.max(maxVal[node << 1], maxVal[(node << 1) | 1]);
    }

    private int query(int node, int start, int end, int limit) {
        if (start > limit || minVal[node] > 0 || maxVal[node] < 0) return -1;
        if (start == end) return start;
        
        push(node);
        int mid = (start + end) >> 1;
        int res = query(node << 1, start, mid, limit);
        if (res != -1) return res;
        return query((node << 1) | 1, mid + 1, end, limit);
    }
}