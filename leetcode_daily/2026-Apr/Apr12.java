class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        if (n <= 2) return 0;
        
        int[] dp = new int[27];
        int[] nextDp = new int[27];
        
        for (int i = 0; i < 27; i++) {
            dp[i] = 100000;
        }
        dp[26] = 0;
        
        char[] w = word.toCharArray();
        
        for (int i = 1; i < n; i++) {
            int curr = w[i] - 'A';
            int prev = w[i - 1] - 'A';
            
            for (int j = 0; j < 27; j++) {
                nextDp[j] = 100000;
            }
            
            int currRow = curr / 6;
            int currCol = curr % 6;
            int prevRow = prev / 6;
            int prevCol = prev % 6;
            int distPrevCurr = Math.abs(prevRow - currRow) + Math.abs(prevCol - currCol);
            
            for (int j = 0; j <= 26; j++) {
                if (dp[j] != 100000) {
                    int val1 = dp[j] + distPrevCurr;
                    if (val1 < nextDp[j]) {
                        nextDp[j] = val1;
                    }
                    
                    int distJCurr = (j == 26) ? 0 : Math.abs(j / 6 - currRow) + Math.abs(j % 6 - currCol);
                    int val2 = dp[j] + distJCurr;
                    if (val2 < nextDp[prev]) {
                        nextDp[prev] = val2;
                    }
                }
            }
            
            int[] temp = dp;
            dp = nextDp;
            nextDp = temp;
        }
        
        int ans = 100000;
        for (int i = 0; i < 27; i++) {
            if (dp[i] < ans) {
                ans = dp[i];
            }
        }
        return ans;
    }
}