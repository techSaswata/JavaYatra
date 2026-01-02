class Solution {
    public int repeatedNTimes(int[] nums) {
        if (nums[1] == nums[0]) return nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2]) {
                return nums[i];
            }
        }
        return nums[0];
    }
}