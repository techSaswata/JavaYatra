class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        System.arraycopy(nums, 0, arr, 0, n);
        int len = n;
        for (int ops = 0; ops < n; ops++) {
            boolean sorted = true;
            int minSum = Integer.MAX_VALUE;
            int bestIdx = -1;
            for (int i = 0; i < len - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    sorted = false;
                }
                int sum = arr[i] + arr[i + 1];
                if (sum < minSum) {
                    minSum = sum;
                    bestIdx = i;
                }
            }
            if (sorted) {
                return ops;
            }
            arr[bestIdx] = minSum;
            System.arraycopy(arr, bestIdx + 2, arr, bestIdx + 1, len - bestIdx - 2);
            len--;
        }
        return 0;
    }
}