class Solution {
    public long maxSumTrionic(int[] nums) {
        long INF = -1000000000000000L;
        long s1 = nums[0];
        long s2 = INF;
        long s3 = INF;
        long s4 = INF;
        long ans = INF;

        for (int i = 1; i < nums.length; i++) {
            long x = nums[i];
            long prev = nums[i - 1];

            long ns1 = x;
            long ns2 = INF;
            long ns3 = INF;
            long ns4 = INF;

            if (x > prev) {
                ns1 = Math.max(x, s1 + x);
                ns2 = s1 + x;
                if (s3 != INF) ns4 = s3 + x;
                if (s4 != INF) ns4 = Math.max(ns4, s4 + x);
            } else if (x < prev) {
                if (s2 != INF) ns3 = s2 + x;
                if (s3 != INF) ns3 = Math.max(ns3, s3 + x);
            }

            s1 = ns1;
            s2 = ns2;
            s3 = ns3;
            s4 = ns4;

            if (s4 > ans) ans = s4;
        }
        return ans;
    }
}