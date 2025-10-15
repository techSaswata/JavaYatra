package leetcode_daily.Oct;
//lc 3350
import java.util.*;
public class oct15 {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        if (n < 2) return 0;
        int[] x = new int[n];
        for (int i = 0; i < n; i++) x[i] = nums.get(i);
        int[] y = new int[n];
        y[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (x[i] < x[i + 1]) y[i] = y[i + 1] + 1;
            else y[i] = 1;
        }
        int l = 1, r = n / 2, ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            boolean ok = false;
            for (int s = 0; s + 2 * m <= n; s++) {
                if (y[s] >= m && y[s + m] >= m) { ok = true; break; }
            }
            if (ok) { ans = m; l = m + 1; } else { r = m - 1; }
        }
        return ans;
    }
}

