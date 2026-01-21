class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        int i = 0;
        for (int x : nums) {
            if (x == 2) {
                ans[i] = -1;
            } else {
                ans[i] = x - (((x + 1) & -(x + 1)) >> 1);
            }
            i++;
        }
        return ans;
    }
}