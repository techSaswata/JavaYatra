package leetcode_daily.Jan26;
class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < 0) {
                    count++;
                    val = -val;
                }
                sum += val;
                if (val < min) {
                    min = val;
                }
            }
        }
        if ((count & 1) == 1) {
            sum -= 2L * min;
        }
        return sum;
    }
}