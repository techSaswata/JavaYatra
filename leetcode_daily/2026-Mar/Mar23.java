class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[] maxDp = new long[n];
        long[] minDp = new long[n];
        
        maxDp[0] = grid[0][0];
        minDp[0] = grid[0][0];
        
        for (int j = 1; j < n; j++) {
            maxDp[j] = maxDp[j - 1] * grid[0][j];
            minDp[j] = maxDp[j];
        }
        
        for (int i = 1; i < m; i++) {
            maxDp[0] *= grid[i][0];
            minDp[0] = maxDp[0];
            for (int j = 1; j < n; j++) {
                int val = grid[i][j];
                if (val > 0) {
                    maxDp[j] = Math.max(maxDp[j], maxDp[j - 1]) * val;
                    minDp[j] = Math.min(minDp[j], minDp[j - 1]) * val;
                } else if (val < 0) {
                    long prevMax = Math.max(maxDp[j], maxDp[j - 1]);
                    long prevMin = Math.min(minDp[j], minDp[j - 1]);
                    maxDp[j] = prevMin * val;
                    minDp[j] = prevMax * val;
                } else {
                    maxDp[j] = 0;
                    minDp[j] = 0;
                }
            }
        }
        
        if (maxDp[n - 1] < 0) {
            return -1;
        }
        return (int) (maxDp[n - 1] % 1000000007);
    }
}