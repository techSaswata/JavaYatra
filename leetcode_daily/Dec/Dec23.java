import java.util.Arrays;

class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[] maxSuffix = new int[n];
        maxSuffix[n - 1] = events[n - 1][2];
        for (int i = n - 2; i >= 0; i--) {
            maxSuffix[i] = Math.max(events[i][2], maxSuffix[i + 1]);
        }
        int maxVal = maxSuffix[0];
        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - 1;
            int nextIdx = -1;
            int target = events[i][1];
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (events[mid][0] > target) {
                    nextIdx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (nextIdx != -1) {
                maxVal = Math.max(maxVal, events[i][2] + maxSuffix[nextIdx]);
            }
        }
        return maxVal;
    }
}