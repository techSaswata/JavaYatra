class Solution {
    public int minimumCost(int[] nums) {
        int min1 = 100, min2 = 100;
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            if (val < min1) {
                min2 = min1;
                min1 = val;
            } else if (val < min2) {
                min2 = val;
            }
        }
        return nums[0] + min1 + min2;
    }
}