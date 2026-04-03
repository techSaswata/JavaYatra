class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        long[] combined = new long[n];
        for (int i = 0; i < n; i++) {
            combined[i] = ((long) robots[i] << 32) | (distance[i] & 0xFFFFFFFFL);
        }
        java.util.Arrays.sort(combined);
        int[] R = new int[n];
        int[] D = new int[n];
        for (int i = 0; i < n; i++) {
            R[i] = (int) (combined[i] >>> 32);
            D[i] = (int) (combined[i]);
        }
        java.util.Arrays.sort(walls);

        int dp0 = count(walls, R[0] - D[0], R[0] - 1) + count(walls, R[0], R[0]);
        int dp1 = count(walls, R[0], R[0]);

        for (int i = 0; i < n - 1; i++) {
            int limit_R = Math.min(R[i] + D[i], R[i + 1] - 1);
            int limit_L = Math.max(R[i + 1] - D[i + 1], R[i] + 1);

            int c00 = count(walls, limit_L, R[i + 1] - 1);
            int c10;
            if (limit_R >= limit_L) {
                c10 = count(walls, R[i] + 1, R[i + 1] - 1);
            } else {
                c10 = count(walls, R[i] + 1, limit_R) + count(walls, limit_L, R[i + 1] - 1);
            }
            int c11 = count(walls, R[i] + 1, limit_R);

            int w_next = count(walls, R[i + 1], R[i + 1]);

            int next_dp0 = w_next + Math.max(dp0 + c00, dp1 + c10);
            int next_dp1 = w_next + Math.max(dp0, dp1 + c11);

            dp0 = next_dp0;
            dp1 = next_dp1;
        }

        return Math.max(dp0, dp1 + count(walls, R[n - 1] + 1, R[n - 1] + D[n - 1]));
    }

    private int count(int[] W, int A, int B) {
        if (A > B) return 0;
        int left = lowerBound(W, A);
        int right = upperBound(W, B);
        return Math.max(0, right - left);
    }

    private int lowerBound(int[] W, int target) {
        int l = 0, r = W.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (W[m] >= target) r = m;
            else l = m + 1;
        }
        return l;
    }

    private int upperBound(int[] W, int target) {
        int l = 0, r = W.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (W[m] > target) r = m;
            else l = m + 1;
        }
        return l;
    }
}