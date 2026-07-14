class Solution {
    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int v : nums) {
            if (v > maxVal) maxVal = v;
        }
        
        int limit = maxVal;
        int[][] gcd = new int[limit + 1][limit + 1];
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit; j++) {
                if (i == 0) gcd[i][j] = j;
                else if (j == 0) gcd[i][j] = i;
                else {
                    int a = i, b = j;
                    while (b > 0) {
                        int temp = b;
                        b = a % b;
                        a = temp;
                    }
                    gcd[i][j] = a;
                }
            }
        }
        
        int stride = limit + 1;
        int totalSize = stride * stride;
        int[] dp = new int[totalSize];
        int[] next_dp = new int[totalSize];
        dp[0] = 1;
        int MOD = 1000000007;
        
        for (int x : nums) {
            System.arraycopy(dp, 0, next_dp, 0, totalSize);
            for (int i = 0; i <= limit; i++) {
                int ng1 = gcd[i][x];
                int base = i * stride;
                int next_base = ng1 * stride;
                for (int j = 0; j <= limit; j++) {
                    int val = dp[base + j];
                    if (val > 0) {
                        int pos1 = next_base + j;
                        next_dp[pos1] += val;
                        if (next_dp[pos1] >= MOD) next_dp[pos1] -= MOD;
                        
                        int ng2 = gcd[j][x];
                        int pos2 = base + ng2;
                        next_dp[pos2] += val;
                        if (next_dp[pos2] >= MOD) next_dp[pos2] -= MOD;
                    }
                }
            }
            int[] temp = dp;
            dp = next_dp;
            next_dp = temp;
        }
        
        int ans = 0;
        for (int i = 1; i <= limit; i++) {
            ans += dp[i * stride + i];
            if (ans >= MOD) ans -= MOD;
        }
        return ans;
    }
}