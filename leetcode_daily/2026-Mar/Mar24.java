class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];
        long pref = 1;
        for (int i = 0; i < n; i++) {
            int[] row = grid[i];
            int[] prow = p[i];
            for (int j = 0; j < m; j++) {
                prow[j] = (int) pref;
                pref = (pref * row[j]) % 12345;
            }
        }
        long suff = 1;
        for (int i = n - 1; i >= 0; i--) {
            int[] row = grid[i];
            int[] prow = p[i];
            for (int j = m - 1; j >= 0; j--) {
                prow[j] = (int) ((prow[j] * suff) % 12345);
                suff = (suff * row[j]) % 12345;
            }
        }
        return p;
    }
}