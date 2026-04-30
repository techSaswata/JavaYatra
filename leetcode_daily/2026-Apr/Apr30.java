class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        int max_c = Math.min(k, m + n - 1);
        
        int[][] prevRow = new int[n][max_c + 1];
        int[][] currRow = new int[n][max_c + 1];
        
        for (int j = 0; j < n; j++) {
            java.util.Arrays.fill(prevRow[j], -1);
        }
        prevRow[0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                java.util.Arrays.fill(currRow[j], -1);
                if (i == 0 && j == 0) {
                    currRow[0][0] = 0;
                    continue;
                }
                
                int v = grid[i][j];
                int cost_v = (v == 0) ? 0 : 1;
                int score_v = v;
                
                for (int c = cost_v; c <= max_c; c++) {
                    int prev_c = c - cost_v;
                    int max_s = -1;
                    
                    if (i > 0) {
                        int s = prevRow[j][prev_c];
                        if (s > max_s) max_s = s;
                    }
                    if (j > 0) {
                        int s = currRow[j - 1][prev_c];
                        if (s > max_s) max_s = s;
                    }
                    
                    if (max_s != -1) {
                        currRow[j][c] = max_s + score_v;
                    }
                }
            }
            int[][] temp = prevRow;
            prevRow = currRow;
            currRow = temp;
        }
        
        int maxScore = -1;
        for (int c = 0; c <= max_c; c++) {
            if (prevRow[n - 1][c] > maxScore) {
                maxScore = prevRow[n - 1][c];
            }
        }
        
        return maxScore;
    }
}