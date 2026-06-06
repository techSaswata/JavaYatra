class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int rightSum = 0;
        for (int i = 0; i < n; i++) {
            rightSum += nums[i];
        }
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            rightSum -= nums[i];
            int diff = leftSum - rightSum;
            answer[i] = diff >= 0 ? diff : -diff;
            leftSum += nums[i];
        }
        return answer;
    }
}