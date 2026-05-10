class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = -1;
        }
        for (int i = 0; i < n - 1; i++) {
            int currentDP = dp[i];
            if (currentDP != -1) {
                int nextDP = currentDP + 1;
                long val = nums[i];
                for (int j = i + 1; j < n; j++) {
                    long diff = nums[j] - val;
                    if (diff >= -target && diff <= target) {
                        if (nextDP > dp[j]) {
                            dp[j] = nextDP;
                        }
                    }
                }
            }
        }
        return dp[n - 1];
    }
}