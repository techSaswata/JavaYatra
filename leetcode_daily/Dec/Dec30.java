class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        if (R < 3 || C < 3) return 0;
        int ans = 0;
        for (int r = 0; r < R - 2; r++) {
            int[] r0 = grid[r];
            int[] r1 = grid[r + 1];
            int[] r2 = grid[r + 2];
            for (int c = 0; c < C - 2; c++) {
                if (r1[c + 1] != 5) continue;
                int v1 = r0[c], v2 = r0[c + 1], v3 = r0[c + 2];
                int v4 = r1[c], v6 = r1[c + 2];
                int v7 = r2[c], v8 = r2[c + 1], v9 = r2[c + 2];
                if (v1 + v9 != 10 || v3 + v7 != 10) continue;
                if (v1 + v2 + v3 != 15 || v7 + v8 + v9 != 15) continue;
                if (v1 + v4 + v7 != 15 || v3 + v6 + v9 != 15) continue;
                int mask = (1 << v1) | (1 << v2) | (1 << v3) | (1 << v4) | 32 | (1 << v6) | (1 << v7) | (1 << v8) | (1 << v9);
                if (mask == 1022) ans++;
            }
        }
        return ans;
    }
}