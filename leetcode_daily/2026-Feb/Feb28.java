class Solution {
    public int concatenatedBinary(int n) {
        long result = 0;
        final int MOD = 1000000007;
        int len = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                len++;
            }
            result = ((result << len) | i) % MOD;
        }
        return (int) result;
    }
}