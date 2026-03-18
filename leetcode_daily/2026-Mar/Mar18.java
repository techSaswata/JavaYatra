class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int c = n;
        int[] colSum = new int[n];
        
        for (int i = 0; i < m; i++) {
            int currentPrefix = 0;
            for (int j = 0; j < c; j++) {
                colSum[j] += grid[i][j];
                currentPrefix += colSum[j];
                if (currentPrefix <= k) {
                    count++;
                } else {
                    c = j;
                    break;
                }
            }
            if (c == 0) {
                break;
            }
        }
        
        return count;
    }
}