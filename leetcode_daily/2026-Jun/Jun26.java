class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] freq = new int[2 * n + 1];
        int offset = n;
        freq[offset] = 1;
        long ans = 0;
        int cnt = 0;
        int sum = offset;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                cnt += freq[sum];
                sum++;
            } else {
                sum--;
                cnt -= freq[sum];
            }
            ans += cnt;
            freq[sum]++;
        }
        
        return ans;
    }
}