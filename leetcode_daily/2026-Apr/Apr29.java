class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        if (n == 1) return 0;

        long[][] prefSum = new long[n + 1][n];
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                prefSum[r + 1][c] = prefSum[r][c] + grid[r][c];
            }
        }

        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = i >= j ? 0 : prefSum[j][0] - prefSum[i][0];
            }
        }

        long[] pref = new long[n + 1];
        long[] suff = new long[n + 2];
        suff[n + 1] = -1;

        for (int c = 2; c < n; c++) {
            long[][] next_dp = new long[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                pref[0] = dp[0][i];
                for (int k = 1; k <= n; k++) {
                    pref[k] = Math.max(pref[k - 1], dp[k][i]);
                }
                
                for (int k = n; k >= 0; k--) {
                    long cost = i >= k ? 0 : prefSum[k][c - 1] - prefSum[i][c - 1];
                    suff[k] = Math.max(suff[k + 1], dp[k][i] + cost);
                }
                
                for (int j = 0; j <= n; j++) {
                    long costJ = i >= j ? 0 : prefSum[j][c - 1] - prefSum[i][c - 1];
                    long val1 = pref[j] + costJ;
                    long val2 = suff[j + 1];
                    next_dp[i][j] = Math.max(val1, val2);
                }
            }
            dp = next_dp;
        }

        long ans = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                long costFinal = j >= i ? 0 : prefSum[i][n - 1] - prefSum[j][n - 1];
                ans = Math.max(ans, dp[i][j] + costFinal);
            }
        }

        return ans;
    }
}