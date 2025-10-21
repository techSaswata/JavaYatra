package leetcode_daily.Oct;
// lc 3346
import java.util.*;
public class oct21 {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = 0, maxWindow = 0;
        for (int r = 0; r < n; r++) {
            while (nums[r] - nums[l] > 2L * k) l++;
            maxWindow = Math.max(maxWindow, r - l + 1);
        }
        int ans = Math.min(maxWindow, numOperations);
        int i = 0;
        while (i < n) {
            int val = nums[i];
            int j = i;
            while (j < n && nums[j] == val) j++;
            int countAlready = j - i;
            int left = lowerBound(nums, val - k);
            int right = upperBound(nums, val + k) - 1;
            int countFixable = 0;
            if (right >= left) countFixable = right - left + 1;
            ans = Math.max(ans, Math.min(countFixable, countAlready + numOperations));
            i = j;
        }
        return ans;
    }

    private int lowerBound(int[] a, int t) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] < t) l = m + 1;
            else r = m;
        }
        return l;
    }
    private int upperBound(int[] a, int t) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] <= t) l = m + 1;
            else r = m;
        }
        return l;
    }
}
