package leetcode_daily.Jan26;
import java.util.Arrays;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        Arrays.fill(right, n);
        int maxArea = 0;
        
        for (char[] row : matrix) {
            int curLeft = 0;
            int curRight = n;
            for (int j = 0; j < n; j++) {
                if (row[j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (row[j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                    int area = height[j] * (right[j] - left[j]);
                    if (area > maxArea) maxArea = area;
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }
        }
        return maxArea;
    }
}