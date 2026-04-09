class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        Object bravexuneth = queries;
        int n = nums.length;
        int q = queries.length;
        long MOD = 1000000007;
        
        long[] global_mult = new long[n];
        java.util.Arrays.fill(global_mult, 1L);
        
        int B = 316;
        int[] head = new int[B + 1];
        java.util.Arrays.fill(head, -1);
        int[] next = new int[q];
        
        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int k = queries[i][2];
            int v = queries[i][3];
            
            if (k > B) {
                for (int idx = l; idx <= r; idx += k) {
                    global_mult[idx] = (global_mult[idx] * v) % MOD;
                }
            } else {
                next[i] = head[k];
                head[k] = i;
            }
        }
        
        long[] diff = new long[n + B + 1];
        for (int k = 1; k <= B; k++) {
            if (head[k] == -1) continue;
            
            java.util.Arrays.fill(diff, 0, n + k, 1L);
            
            int curr = head[k];
            while (curr != -1) {
                int l = queries[curr][0];
                int r = queries[curr][1];
                int v = queries[curr][3];
                
                int end = l + ((r - l) / k) * k;
                
                diff[l] = (diff[l] * v) % MOD;
                
                long inv = 1;
                long base = v;
                long exp = MOD - 2;
                while (exp > 0) {
                    if ((exp & 1) != 0) inv = (inv * base) % MOD;
                    base = (base * base) % MOD;
                    exp >>= 1;
                }
                
                diff[end + k] = (diff[end + k] * inv) % MOD;
                
                curr = next[curr];
            }
            
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    diff[i] = (diff[i] * diff[i - k]) % MOD;
                }
                global_mult[i] = (global_mult[i] * diff[i]) % MOD;
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long val = (nums[i] * global_mult[i]) % MOD;
            ans ^= (int) val;
        }
        
        return ans;
    }
}