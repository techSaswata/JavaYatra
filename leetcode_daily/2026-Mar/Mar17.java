class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < m; i++) {
            java.util.Arrays.sort(matrix[i]);
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) break;
                int area = matrix[i][j] * (n - j);
                if (area > ans) {
                    ans = area;
                }
            }
        }
        
        return ans;
    }
}