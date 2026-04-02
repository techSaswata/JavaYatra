class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        int[] dp0 = new int[n];
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        int MIN = -1_000_000_000;
        for (int j = 0; j < n; j++) {
            dp0[j] = MIN;
            dp1[j] = MIN;
            dp2[j] = MIN;
        }
        
        for (int i = 0; i < m; i++) {
            int[] row = coins[i];
            for (int j = 0; j < n; j++) {
                int v = row[j];
                if (i == 0 && j == 0) {
                    dp0[0] = v;
                    dp1[0] = v < 0 ? 0 : v;
                    dp2[0] = v < 0 ? 0 : v;
                } else {
                    int p0 = MIN, p1 = MIN, p2 = MIN;
                    
                    if (i > 0) {
                        p0 = dp0[j];
                        p1 = dp1[j];
                        p2 = dp2[j];
                    }
                    if (j > 0) {
                        if (dp0[j-1] > p0) p0 = dp0[j-1];
                        if (dp1[j-1] > p1) p1 = dp1[j-1];
                        if (dp2[j-1] > p2) p2 = dp2[j-1];
                    }
                    
                    int n0 = p0 + v;
                    int n1 = p1 + v;
                    int n2 = p2 + v;
                    
                    if (v < 0) {
                        if (p0 > n1) n1 = p0;
                        if (p1 > n2) n2 = p1;
                    }
                    
                    dp0[j] = n0;
                    dp1[j] = n1;
                    dp2[j] = n2;
                }
            }
        }
        
        return dp2[n-1];
    }
}