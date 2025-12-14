class Solution {
    public int numberOfWays(String corridor) {
        long ans = 1;
        int seats = 0;
        int prev = -1;
        int n = corridor.length();
        long mod = 1_000_000_007L;
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i) == 'S') {
                seats++;
                if (seats > 2 && (seats & 1) == 1) {
                    ans = (ans * (i - prev)) % mod;
                }
                prev = i;
            }
        }
        return seats > 0 && (seats & 1) == 0 ? (int) ans : 0;
    }
}