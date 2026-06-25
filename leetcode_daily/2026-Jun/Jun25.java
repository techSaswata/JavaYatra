class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] count = new int[2 * n + 2];
        int curr = n + 1;
        count[curr] = 1;
        int lessCount = 0;
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                lessCount += count[curr];
                curr++;
            } else {
                curr--;
                lessCount -= count[curr];
            }
            ans += lessCount;
            count[curr]++;
        }
        
        return ans;
    }
}