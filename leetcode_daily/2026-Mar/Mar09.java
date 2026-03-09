class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1000000007;
        int[][] dp0 = new int[zero + 1][one + 1];
        int[][] dp1 = new int[zero + 1][one + 1];
        
        for (int i = 1; i <= zero && i <= limit; i++) {
            dp0[i][0] = 1;
        }
        for (int j = 1; j <= one && j <= limit; j++) {
            dp1[0][j] = 1;
        }
        
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                int val0 = dp0[i - 1][j] + dp1[i - 1][j];
                if (val0 >= MOD) val0 -= MOD;
                if (i > limit) {
                    val0 -= dp1[i - limit - 1][j];
                    if (val0 < 0) val0 += MOD;
                }
                dp0[i][j] = val0;
                
                int val1 = dp0[i][j - 1] + dp1[i][j - 1];
                if (val1 >= MOD) val1 -= MOD;
                if (j > limit) {
                    val1 -= dp0[i][j - limit - 1];
                    if (val1 < 0) val1 += MOD;
                }
                dp1[i][j] = val1;
            }
        }
        
        int ans = dp0[zero][one] + dp1[zero][one];
        if (ans >= MOD) ans -= MOD;
        return ans;
    }
}