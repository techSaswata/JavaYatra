class Solution {
    int[] dp;
    
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        dp = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == 0) {
                dfs(arr, n, d, i);
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
    
    private int dfs(int[] arr, int n, int d, int i) {
        if (dp[i] != 0) {
            return dp[i];
        }
        int res = 1;
        int limitRight = i + d;
        if (limitRight >= n) {
            limitRight = n - 1;
        }
        for (int j = i + 1; j <= limitRight; j++) {
            if (arr[j] >= arr[i]) {
                break;
            }
            int val = 1 + dfs(arr, n, d, j);
            if (val > res) {
                res = val;
            }
        }
        int limitLeft = i - d;
        if (limitLeft < 0) {
            limitLeft = 0;
        }
        for (int j = i - 1; j >= limitLeft; j--) {
            if (arr[j] >= arr[i]) {
                break;
            }
            int val = 1 + dfs(arr, n, d, j);
            if (val > res) {
                res = val;
            }
        }
        return dp[i] = res;
    }
}