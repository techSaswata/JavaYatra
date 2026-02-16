import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            long val = nums[right];
            while ((long) nums[left] * k < val) {
                left++;
            }
            int len = right - left + 1;
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return n - maxLen;
    }
}