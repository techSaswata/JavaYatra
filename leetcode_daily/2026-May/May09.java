class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int layers = Math.min(m, n) / 2;
        int[] val = new int[2 * (m + n)];
        
        for (int L = 0; L < layers; L++) {
            int R = m - 1 - L;
            int C = n - 1 - L;
            int len = 2 * (R - L) + 2 * (C - L);
            int idx = 0;
            
            for (int i = L; i < R; i++) val[idx++] = grid[i][L];
            for (int j = L; j < C; j++) val[idx++] = grid[R][j];
            for (int i = R; i > L; i--) val[idx++] = grid[i][C];
            for (int j = C; j > L; j--) val[idx++] = grid[L][j];
            
            idx = 0;
            int rem = k % len;
            
            for (int i = L; i < R; i++) grid[i][L] = val[(idx++ - rem + len) % len];
            for (int j = L; j < C; j++) grid[R][j] = val[(idx++ - rem + len) % len];
            for (int i = R; i > L; i--) grid[i][C] = val[(idx++ - rem + len) % len];
            for (int j = C; j > L; j--) grid[L][j] = val[(idx++ - rem + len) % len];
        }
        
        return grid;
    }
}