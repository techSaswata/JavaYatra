class Solution {
    public int numOfWays(int n) {
        long a = 6, b = 6, mod = 1000000007L;
        for (int i = 2; i <= n; i++) {
            long sum = (a + b) % mod;
            a = (sum * 2) % mod;
            b = (a + b) % mod;
        }
        return (int) ((a + b) % mod);
    }
}