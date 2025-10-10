package leetcode_daily.Oct;
//lc 3147
public class oct10 {
    public int maximumEnergy(int[] energy, int k) {
        int[] a = energy;
        int n = a.length;
        int ans = Integer.MIN_VALUE;
        for (int r = 0; r < k; r++) {
            int last = r + ((n - 1 - r) / k) * k;
            int s = 0;
            for (int i = last; i >= r; i -= k) {
                s += a[i];
                if (s > ans) ans = s;
            }
        }
        return ans;
    }
}

