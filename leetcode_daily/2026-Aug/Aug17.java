class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if (n <= 1) return 0;
        
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        int[][] dp = new int[n][n];
        int[][] max_l = new int[n][n];
        int[][] max_r = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            max_l[i][i] = stoneValue[i];
            max_r[i][i] = stoneValue[i];
            int m = i - 1;
            for (int j = i + 1; j < n; j++) {
                int totalSum = prefix[j + 1] - prefix[i];
                while (m + 1 < j && (prefix[m + 2] - prefix[i]) * 2 <= totalSum) {
                    m++;
                }
                
                int ans = 0;
                if (m >= i) {
                    ans = Math.max(ans, max_l[i][m]);
                    if ((prefix[m + 1] - prefix[i]) * 2 == totalSum) {
                        ans = Math.max(ans, max_r[m + 1][j]);
                    }
                }
                if (m + 1 < j) {
                    ans = Math.max(ans, max_r[m + 2][j]);
                }
                dp[i][j] = ans;
                
                max_l[i][j] = Math.max(max_l[i][j - 1], totalSum + ans);
                max_r[i][j] = Math.max(max_r[i + 1][j], totalSum + ans);
            }
        }
        
        return dp[0][n - 1];
    }
}