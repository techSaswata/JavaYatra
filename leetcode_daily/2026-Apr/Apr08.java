class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int THRESHOLD = (int) Math.sqrt(n) + 1;
        int[][] diff = new int[THRESHOLD][n + THRESHOLD];
        for (int i = 1; i < THRESHOLD; i++) {
            for (int j = 0; j < n + THRESHOLD; j++) {
                diff[i][j] = 1;
            }
        }
        
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k >= THRESHOLD) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) (((long) nums[i] * v) % 1000000007);
                }
            } else {
                int end = l + ((r - l) / k) * k;
                diff[k][l] = (int) (((long) diff[k][l] * v) % 1000000007);
                int invV = inv(v);
                diff[k][end + k] = (int) (((long) diff[k][end + k] * invV) % 1000000007);
            }
        }
        
        for (int k = 1; k < THRESHOLD; k++) {
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    diff[k][i] = (int) (((long) diff[k][i] * diff[k][i - k]) % 1000000007);
                }
                if (diff[k][i] != 1) {
                    nums[i] = (int) (((long) nums[i] * diff[k][i]) % 1000000007);
                }
            }
        }
        
        int xor = 0;
        for (int x : nums) {
            xor ^= x;
        }
        return xor;
    }
    
    private int inv(int a) {
        int m = 1000000007, m0 = m, y = 0, x = 1;
        while (a > 1) {
            int q = a / m;
            int t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        return x < 0 ? x + m0 : x;
    }
}