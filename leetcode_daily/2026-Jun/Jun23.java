class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;
        int MOD = 1000000007;
        
        int[] prev = new int[k + 1];
        for (int v = 1; v <= k; v++) {
            prev[v] = v - 1;
        }
        
        int[] curr = new int[k + 1];
        for (int i = 3; i <= n; i++) {
            curr[1] = 0;
            for (int v = 2; v <= k; v++) {
                curr[v] = curr[v - 1] + prev[k - v + 2];
                if (curr[v] >= MOD) {
                    curr[v] -= MOD;
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        int sum = 0;
        for (int v = 1; v <= k; v++) {
            sum += prev[v];
            if (sum >= MOD) {
                sum -= MOD;
            }
        }
        
        return (int) ((sum * 2L) % MOD);
    }
}