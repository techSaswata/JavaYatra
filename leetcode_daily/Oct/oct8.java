package leetcode_daily.Oct;
//lc 2300
import java.util.Arrays;
public class oct8 {
    public int[] successfulPairs(int[] s, int[] p, long k) {
        Arrays.sort(p);
        int[] r = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            long need = (k + s[i] - 1) / s[i];
            int idx = bs(p, need);
            r[i] = p.length - idx;
        }
        return r;
    }

    int bs(int[] a, long x) {
        int l = 0, h = a.length;
        while (l < h) {
            int m = (l + h) / 2;
            if (a[m] < x)
                l = m + 1;
            else
                h = m;
        }
        return l;

    }
}