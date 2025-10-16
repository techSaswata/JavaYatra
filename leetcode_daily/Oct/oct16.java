package leetcode_daily.Oct;
//lc 2598
public class oct16 {
        public int findSmallestInteger(int[] nums, int value) {
        int v = value;
        int[] c = new int[v];
        for (int x : nums) {
            int r = x % v;
            if (r < 0) r += v;
            c[r]++;
        }
        int m = 0;
        while (true) {
            int r = m % v;
            if (c[r] == 0) return m;
            c[r]--;
            m++;
        }
    }
}


