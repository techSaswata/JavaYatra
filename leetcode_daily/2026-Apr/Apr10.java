class Solution {
    public int minimumDistance(int[] nums) {
        int max = 0;
        for (int v : nums) {
            if (v > max) {
                max = v;
            }
        }
        int[] last1 = new int[max + 1];
        int[] last2 = new int[max + 1];
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            int l2 = last2[v];
            if (l2 != 0) {
                int dist = (i - l2 + 1) << 1;
                if (dist < minDistance) {
                    minDistance = dist;
                }
            }
            last2[v] = last1[v];
            last1[v] = i + 1;
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}