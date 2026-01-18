class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
            }
        }

        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i <= m - k; i++) {
                nextSquare:
                for (int j = 0; j <= n - k; j++) {
                    int target = rowSum[i][j + k] - rowSum[i][j];

                    for (int r = i + 1; r < i + k; r++) {
                        if (rowSum[r][j + k] - rowSum[r][j] != target) {
                            continue nextSquare;
                        }
                    }

                    for (int c = j; c < j + k; c++) {
                        if (colSum[i + k][c] - colSum[i][c] != target) {
                            continue nextSquare;
                        }
                    }

                    int d1 = 0, d2 = 0;
                    for (int d = 0; d < k; d++) {
                        d1 += grid[i + d][j + d];
                        d2 += grid[i + d][j + k - 1 - d];
                    }

                    if (d1 == target && d2 == target) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }
}