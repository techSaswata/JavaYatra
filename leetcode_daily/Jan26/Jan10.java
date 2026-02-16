package leetcode_daily.Jan26;
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        if (c1.length < c2.length) {
            char[] temp = c1;
            c1 = c2;
            c2 = temp;
        }
        int m = c1.length;
        int n = c2.length;
        int sum = 0;
        for (char c : c1) sum += c;
        for (char c : c2) sum += c;
        int[] dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int prev = 0;
            char c = c1[i];
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (c == c2[j - 1]) {
                    dp[j] = prev + c;
                } else if (dp[j - 1] > dp[j]) {
                    dp[j] = dp[j - 1];
                }
                prev = temp;
            }
        }
        return sum - 2 * dp[n];
    }
}