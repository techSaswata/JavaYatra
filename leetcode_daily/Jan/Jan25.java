class Solution {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) return 0;
        java.util.Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int limit = nums.length - k;
        for (int i = 0; i <= limit; i++) {
            int diff = nums[i + k - 1] - nums[i];
            if (diff < min) {
                min = diff;
            }
        }
        return min;
    }
}