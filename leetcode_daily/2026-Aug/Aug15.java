class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean nonZero = false;
        for (int x : nums) {
            xor ^= x;
            if (x != 0) {
                nonZero = true;
            }
        }
        if (!nonZero) {
            return 0;
        }
        return xor == 0 ? nums.length - 1 : nums.length;
    }
}