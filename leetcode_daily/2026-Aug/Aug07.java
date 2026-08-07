class Solution {
    int[][] dp;

    public String smallestNumber(String num, long t) {
        long temp = t;
        int req2 = 0, req3 = 0, req5 = 0, req7 = 0;
        while (temp % 2 == 0) { req2++; temp /= 2; }
        while (temp % 3 == 0) { req3++; temp /= 3; }
        while (temp % 5 == 0) { req5++; temp /= 5; }
        while (temp % 7 == 0) { req7++; temp /= 7; }
        if (temp > 1) return "-1";

        dp = new int[61][41];
        for (int i = 0; i <= 60; i++) {
            for (int j = 0; j <= 40; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE - 1;
                if (i > 0) {
                    min = Math.min(min, dp[Math.max(0, i - 1)][j]);
                    min = Math.min(min, dp[Math.max(0, i - 2)][j]);
                    min = Math.min(min, dp[Math.max(0, i - 3)][j]);
                }
                if (j > 0) {
                    min = Math.min(min, dp[i][Math.max(0, j - 1)]);
                    min = Math.min(min, dp[i][Math.max(0, j - 2)]);
                }
                if (i > 0 || j > 0) {
                    min = Math.min(min, dp[Math.max(0, i - 1)][Math.max(0, j - 1)]);
                }
                dp[i][j] = 1 + min;
            }
        }

        int n = num.length();
        int[] pref2 = new int[n + 1];
        int[] pref3 = new int[n + 1];
        int[] pref5 = new int[n + 1];
        int[] pref7 = new int[n + 1];

        int z_idx = num.indexOf('0');
        for (int i = 0; i < n; i++) {
            pref2[i + 1] = pref2[i];
            pref3[i + 1] = pref3[i];
            pref5[i + 1] = pref5[i];
            pref7[i + 1] = pref7[i];
            int d = num.charAt(i) - '0';
            if (d == 0) break;
            if (d == 2) pref2[i + 1]++;
            else if (d == 3) pref3[i + 1]++;
            else if (d == 4) pref2[i + 1] += 2;
            else if (d == 5) pref5[i + 1]++;
            else if (d == 6) { pref2[i + 1]++; pref3[i + 1]++; }
            else if (d == 7) pref7[i + 1]++;
            else if (d == 8) pref2[i + 1] += 3;
            else if (d == 9) pref3[i + 1] += 2;
        }

        if (z_idx == -1) {
            int r2 = Math.max(0, req2 - pref2[n]);
            int r3 = Math.max(0, req3 - pref3[n]);
            int r5 = Math.max(0, req5 - pref5[n]);
            int r7 = Math.max(0, req7 - pref7[n]);
            if (r2 == 0 && r3 == 0 && r5 == 0 && r7 == 0) {
                return num;
            }
            z_idx = n;
        }

        int best_i = -1;
        int best_d = -1;

        for (int i = Math.min(n - 1, z_idx); i >= 0; i--) {
            int start_d = (num.charAt(i) - '0') + 1;
            if (num.charAt(i) == '0') start_d = 1;

            for (int d = start_d; d <= 9; d++) {
                int p2 = pref2[i], p3 = pref3[i], p5 = pref5[i], p7 = pref7[i];
                if (d == 2) p2++;
                else if (d == 3) p3++;
                else if (d == 4) p2 += 2;
                else if (d == 5) p5++;
                else if (d == 6) { p2++; p3++; }
                else if (d == 7) p7++;
                else if (d == 8) p2 += 3;
                else if (d == 9) p3 += 2;

                int r2 = Math.max(0, req2 - p2);
                int r3 = Math.max(0, req3 - p3);
                int r5 = Math.max(0, req5 - p5);
                int r7 = Math.max(0, req7 - p7);

                int rem_len = n - 1 - i;
                if (check(rem_len, r2, r3, r5, r7)) {
                    best_i = i;
                    best_d = d;
                    break;
                }
            }
            if (best_i != -1) break;
        }

        if (best_i != -1) {
            char[] res = new char[n];
            for (int j = 0; j < best_i; j++) res[j] = num.charAt(j);
            res[best_i] = (char) ('0' + best_d);

            int cur_p2 = pref2[best_i], cur_p3 = pref3[best_i], cur_p5 = pref5[best_i], cur_p7 = pref7[best_i];
            if (best_d == 2) cur_p2++;
            else if (best_d == 3) cur_p3++;
            else if (best_d == 4) cur_p2 += 2;
            else if (best_d == 5) cur_p5++;
            else if (best_d == 6) { cur_p2++; cur_p3++; }
            else if (best_d == 7) cur_p7++;
            else if (best_d == 8) cur_p2 += 3;
            else if (best_d == 9) cur_p3 += 2;

            for (int j = best_i + 1; j < n; j++) {
                for (int d = 1; d <= 9; d++) {
                    int np2 = cur_p2, np3 = cur_p3, np5 = cur_p5, np7 = cur_p7;
                    if (d == 2) np2++;
                    else if (d == 3) np3++;
                    else if (d == 4) np2 += 2;
                    else if (d == 5) np5++;
                    else if (d == 6) { np2++; np3++; }
                    else if (d == 7) np7++;
                    else if (d == 8) np2 += 3;
                    else if (d == 9) np3 += 2;

                    int r2 = Math.max(0, req2 - np2);
                    int r3 = Math.max(0, req3 - np3);
                    int r5 = Math.max(0, req5 - np5);
                    int r7 = Math.max(0, req7 - np7);

                    int rem_len = n - 1 - j;
                    if (check(rem_len, r2, r3, r5, r7)) {
                        res[j] = (char) ('0' + d);
                        cur_p2 = np2; cur_p3 = np3; cur_p5 = np5; cur_p7 = np7;
                        break;
                    }
                }
            }
            return new String(res);
        } else {
            int L = Math.max(n + 1, req5 + req7 + dp[req2][req3]);
            char[] res = new char[L];
            int cur_p2 = 0, cur_p3 = 0, cur_p5 = 0, cur_p7 = 0;
            for (int j = 0; j < L; j++) {
                for (int d = 1; d <= 9; d++) {
                    int np2 = cur_p2, np3 = cur_p3, np5 = cur_p5, np7 = cur_p7;
                    if (d == 2) np2++;
                    else if (d == 3) np3++;
                    else if (d == 4) np2 += 2;
                    else if (d == 5) np5++;
                    else if (d == 6) { np2++; np3++; }
                    else if (d == 7) np7++;
                    else if (d == 8) np2 += 3;
                    else if (d == 9) np3 += 2;

                    int r2 = Math.max(0, req2 - np2);
                    int r3 = Math.max(0, req3 - np3);
                    int r5 = Math.max(0, req5 - np5);
                    int r7 = Math.max(0, req7 - np7);

                    int rem_len = L - 1 - j;
                    if (check(rem_len, r2, r3, r5, r7)) {
                        res[j] = (char) ('0' + d);
                        cur_p2 = np2; cur_p3 = np3; cur_p5 = np5; cur_p7 = np7;
                        break;
                    }
                }
            }
            return new String(res);
        }
    }

    private boolean check(int rem_len, int r2, int r3, int r5, int r7) {
        if (r5 + r7 > rem_len) return false;
        int rem_for_23 = rem_len - r5 - r7;
        return dp[r2][r3] <= rem_for_23;
    }
}