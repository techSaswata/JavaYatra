class Solution {
    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long num) {
        if (num < 100) return 0;
        String s = Long.toString(num);
        int n = s.length();
        long[] memo = new long[n * 968];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
        return dp(s, 0, 10, 10, 0, 0, memo)[1];
    }

    private long[] dp(String s, int i, int d1, int d2, int is_less, int is_started, long[] memo) {
        if (i == s.length()) {
            return new long[]{1, 0};
        }
        
        int idx = i * 968 + d1 * 88 + d2 * 8 + is_less * 4 + is_started * 2;
        if (memo[idx] != -1) {
            return new long[]{memo[idx], memo[idx + 1]};
        }
        
        int limit = is_less == 1 ? 9 : (s.charAt(i) - '0');
        long count = 0;
        long sum = 0;
        
        for (int d = 0; d <= limit; d++) {
            int next_is_less = (is_less == 1 || d < limit) ? 1 : 0;
            if (is_started == 0 && d == 0) {
                long[] sub = dp(s, i + 1, 10, 10, next_is_less, 0, memo);
                count += sub[0];
                sum += sub[1];
            } else {
                long[] sub = dp(s, i + 1, d, d1, next_is_less, 1, memo);
                count += sub[0];
                sum += sub[1];
                if (d2 != 10 && d1 != 10) {
                    if ((d1 > d2 && d1 > d) || (d1 < d2 && d1 < d)) {
                        sum += sub[0];
                    }
                }
            }
        }
        
        memo[idx] = count;
        memo[idx + 1] = sum;
        return new long[]{count, sum};
    }
}