package leetcode_daily.Jan26;
import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        int i = 0;
        for (int num : nums) {
            if (num == 2) {
                ans[i++] = -1;
            } else {
                ans[i++] = num - (1 << (Integer.numberOfTrailingZeros(~num) - 1));
            }
        }
        return ans;
    }
}