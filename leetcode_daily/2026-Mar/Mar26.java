class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] top_counts = new int[100005];
        int[] bottom_counts = new int[100005];
        long S_top = 0;
        long S_bottom = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                bottom_counts[grid[i][j]]++;
                S_bottom += grid[i][j];
            }
        }
        
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                top_counts[v]++;
                bottom_counts[v]--;
                S_top += v;
                S_bottom -= v;
            }
            long diff = S_top - S_bottom;
            if (diff == 0) return true;
            if (diff > 0 && diff <= 100000) {
                if (valid_in_top(diff, i, n, top_counts, grid)) return true;
            } else if (diff < 0 && -diff <= 100000) {
                if (valid_in_bottom(-diff, i, m, n, bottom_counts, grid)) return true;
            }
        }
        
        int[] left_counts = new int[100005];
        int[] right_counts = new int[100005];
        long S_left = 0;
        long S_right = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                right_counts[grid[i][j]]++;
                S_right += grid[i][j];
            }
        }
        
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int v = grid[i][j];
                left_counts[v]++;
                right_counts[v]--;
                S_left += v;
                S_right -= v;
            }
            long diff = S_left - S_right;
            if (diff == 0) return true;
            if (diff > 0 && diff <= 100000) {
                if (valid_in_left(diff, j, m, left_counts, grid)) return true;
            } else if (diff < 0 && -diff <= 100000) {
                if (valid_in_right(-diff, j, m, n, right_counts, grid)) return true;
            }
        }
        
        return false;
    }

    private boolean valid_in_top(long x, int i, int n, int[] top_counts, int[][] grid) {
        if (x <= 0 || x > 100000) return false;
        int h = i + 1;
        int w = n;
        if (h >= 2 && w >= 2) {
            return top_counts[(int)x] > 0;
        } else if (h == 1 && w >= 2) {
            return x == grid[0][0] || x == grid[0][n - 1];
        } else if (w == 1 && h >= 2) {
            return x == grid[0][0] || x == grid[i][0];
        }
        return false;
    }

    private boolean valid_in_bottom(long x, int i, int m, int n, int[] bottom_counts, int[][] grid) {
        if (x <= 0 || x > 100000) return false;
        int h = m - 1 - i;
        int w = n;
        if (h >= 2 && w >= 2) {
            return bottom_counts[(int)x] > 0;
        } else if (h == 1 && w >= 2) {
            return x == grid[i + 1][0] || x == grid[i + 1][n - 1];
        } else if (w == 1 && h >= 2) {
            return x == grid[i + 1][0] || x == grid[m - 1][0];
        }
        return false;
    }

    private boolean valid_in_left(long x, int j, int m, int[] left_counts, int[][] grid) {
        if (x <= 0 || x > 100000) return false;
        int h = m;
        int w = j + 1;
        if (h >= 2 && w >= 2) {
            return left_counts[(int)x] > 0;
        } else if (h == 1 && w >= 2) {
            return x == grid[0][0] || x == grid[0][j];
        } else if (w == 1 && h >= 2) {
            return x == grid[0][0] || x == grid[m - 1][0];
        }
        return false;
    }

    private boolean valid_in_right(long x, int j, int m, int n, int[] right_counts, int[][] grid) {
        if (x <= 0 || x > 100000) return false;
        int h = m;
        int w = n - 1 - j;
        if (h >= 2 && w >= 2) {
            return right_counts[(int)x] > 0;
        } else if (h == 1 && w >= 2) {
            return x == grid[0][j + 1] || x == grid[0][n - 1];
        } else if (w == 1 && h >= 2) {
            return x == grid[0][j + 1] || x == grid[m - 1][j + 1];
        }
        return false;
    }
}