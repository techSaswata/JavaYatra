package leetcode_daily.Jan26;
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] s = new int[m + 1][n + 1];
        int len = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = mat[i - 1][j - 1] + s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1];
                if (i > len && j > len) {
                    if (s[i][j] - s[i - len - 1][j] - s[i][j - len - 1] + s[i - len - 1][j - len - 1] <= threshold) {
                        len++;
                    }
                }
            }
        }
        return len;
    }
}