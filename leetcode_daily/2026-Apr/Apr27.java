class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        if (m == 1 && n == 1) return true;
        
        int[] connects = {0, 10, 5, 12, 6, 9, 3};
        
        int[] q = new int[m * n * 2];
        int head = 0, tail = 0;
        q[tail++] = 0;
        q[tail++] = 0;
        
        grid[0][0] |= 8;
        
        while (head < tail) {
            int r = q[head++];
            int c = q[head++];
            
            if (r == m - 1 && c == n - 1) return true;
            
            int type = grid[r][c] & 7;
            int mask = connects[type];
            
            if ((mask & 1) != 0) {
                int nr = r - 1;
                if (nr >= 0) {
                    int nval = grid[nr][c];
                    if ((nval & 8) == 0 && (connects[nval & 7] & 4) != 0) {
                        grid[nr][c] |= 8;
                        q[tail++] = nr;
                        q[tail++] = c;
                    }
                }
            }
            if ((mask & 2) != 0) {
                int nc = c + 1;
                if (nc < n) {
                    int nval = grid[r][nc];
                    if ((nval & 8) == 0 && (connects[nval & 7] & 8) != 0) {
                        grid[r][nc] |= 8;
                        q[tail++] = r;
                        q[tail++] = nc;
                    }
                }
            }
            if ((mask & 4) != 0) {
                int nr = r + 1;
                if (nr < m) {
                    int nval = grid[nr][c];
                    if ((nval & 8) == 0 && (connects[nval & 7] & 1) != 0) {
                        grid[nr][c] |= 8;
                        q[tail++] = nr;
                        q[tail++] = c;
                    }
                }
            }
            if ((mask & 8) != 0) {
                int nc = c - 1;
                if (nc >= 0) {
                    int nval = grid[r][nc];
                    if ((nval & 8) == 0 && (connects[nval & 7] & 2) != 0) {
                        grid[r][nc] |= 8;
                        q[tail++] = r;
                        q[tail++] = nc;
                    }
                }
            }
        }
        
        return false;
    }
}