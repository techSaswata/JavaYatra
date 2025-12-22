class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        char[][] cols = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cols[j][i] = strs[i].charAt(j);
            }
        }
        
        int[] dp = new int[m];
        int maxLen = 0;
        
        for (int j = 0; j < m; j++) {
            dp[j] = 1;
            char[] cJ = cols[j];
            for (int i = j - 1; i >= 0; i--) {
                if (dp[i] + 1 <= dp[j]) continue;
                
                char[] cI = cols[i];
                boolean match = true;
                for (int k = 0; k < n; k++) {
                    if (cI[k] > cJ[k]) {
                        match = false;
                        break;
                    }
                }
                
                if (match) {
                    dp[j] = dp[i] + 1;
                }
            }
            if (dp[j] > maxLen) {
                maxLen = dp[j];
            }
        }
        
        return m - maxLen;
    }
}