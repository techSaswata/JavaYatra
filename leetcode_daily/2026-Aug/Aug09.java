class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n + 1];
        int[] suffixSum = new int[n];
        
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m >= n) {
                    dp[i][m] = suffixSum[i];
                } else {
                    int minOpponent = Integer.MAX_VALUE;
                    for (int x = 1; x <= 2 * m; x++) {
                        int nextM = x > m ? x : m;
                        if (dp[i + x][nextM] < minOpponent) {
                            minOpponent = dp[i + x][nextM];
                        }
                    }
                    dp[i][m] = suffixSum[i] - minOpponent;
                }
            }
        }
        
        return dp[0][1];
    }
}