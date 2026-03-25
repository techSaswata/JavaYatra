class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        long[] rowSums = new long[m];
        long[] colSums = new long[n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                rowSums[i] += val;
                colSums[j] += val;
                totalSum += val;
            }
        }
        
        if (totalSum % 2 != 0) {
            return false;
        }
        
        long target = totalSum / 2;
        long currentSum = 0;
        
        for (int i = 0; i < m - 1; i++) {
            currentSum += rowSums[i];
            if (currentSum == target) {
                return true;
            }
        }
        
        currentSum = 0;
        for (int j = 0; j < n - 1; j++) {
            currentSum += colSums[j];
            if (currentSum == target) {
                return true;
            }
        }
        
        return false;
    }
}