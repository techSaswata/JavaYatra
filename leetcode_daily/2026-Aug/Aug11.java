class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        for (int i = 1; i < nums.length && nums[i] == nums[i - 1] + 1; i++) {
            sum += nums[i];
        }
        long mask = 0;
        for (int num : nums) {
            mask |= 1L << num;
        }
        while (sum <= 50 && (mask & (1L << sum)) != 0) {
            sum++;
        }
        return sum;
    }
}