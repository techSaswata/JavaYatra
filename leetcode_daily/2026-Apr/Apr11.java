class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int[] last1 = new int[n + 1];
        int[] last2 = new int[n + 1];
        int min_dist = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            int l2 = last2[v];
            if (l2 != 0) {
                int dist = i - l2 + 1;
                if (dist < min_dist) {
                    min_dist = dist;
                }
            }
            last2[v] = last1[v];
            last1[v] = i + 1;
        }
        
        return min_dist == Integer.MAX_VALUE ? -1 : min_dist * 2;
    }
}