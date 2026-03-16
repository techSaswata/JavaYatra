class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int[] top3 = {-1, -1, -1};
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int L = 0; i + 2 * L < m && j - L >= 0 && j + L < n; L++) {
                    int sum = 0;
                    if (L == 0) {
                        sum = grid[i][j];
                    } else {
                        for (int k = 0; k < L; k++) {
                            sum += grid[i + k][j + k];
                            sum += grid[i + L + k][j + L - k];
                            sum += grid[i + 2 * L - k][j - k];
                            sum += grid[i + L - k][j - L + k];
                        }
                    }
                    if (sum == top3[0] || sum == top3[1] || sum == top3[2]) {
                        continue;
                    }
                    if (sum > top3[0]) {
                        top3[2] = top3[1];
                        top3[1] = top3[0];
                        top3[0] = sum;
                    } else if (sum > top3[1]) {
                        top3[2] = top3[1];
                        top3[1] = sum;
                    } else if (sum > top3[2]) {
                        top3[2] = sum;
                    }
                }
            }
        }
        
        int count = 0;
        if (top3[0] != -1) count++;
        if (top3[1] != -1) count++;
        if (top3[2] != -1) count++;
        
        int[] res = new int[count];
        for (int i = 0; i < count; i++) {
            res[i] = top3[i];
        }
        
        return res;
    }
}