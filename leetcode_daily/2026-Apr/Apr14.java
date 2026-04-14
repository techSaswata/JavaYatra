class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        int n = robot.size();
        int[] rob = new int[n];
        for (int i = 0; i < n; i++) {
            rob[i] = robot.get(i);
        }
        Arrays.sort(rob);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        long[] dp = new long[n + 1];
        Arrays.fill(dp, 1000000000000000000L);
        dp[0] = 0;
        
        for (int[] fact : factory) {
            int pos = fact[0];
            int limit = fact[1];
            for (int i = n; i >= 1; i--) {
                long cost = 0;
                for (int k = 1; k <= limit && k <= i; k++) {
                    cost += Math.abs((long) rob[i - k] - pos);
                    if (dp[i - k] != 1000000000000000000L) {
                        long val = dp[i - k] + cost;
                        if (val < dp[i]) {
                            dp[i] = val;
                        }
                    }
                }
            }
        }
        
        return dp[n];
    }
}