class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        int[] arr = new int[k * k];
        
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                int idx = 0;
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        arr[idx++] = grid[x][y];
                    }
                }
                
                java.util.Arrays.sort(arr);
                
                int minDiff = Integer.MAX_VALUE;
                for (int x = 1; x < arr.length; x++) {
                    int diff = arr[x] - arr[x - 1];
                    if (diff > 0 && diff < minDiff) {
                        minDiff = diff;
                        if (minDiff == 1) {
                            break;
                        }
                    }
                }
                
                ans[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }
        
        return ans;
    }
}