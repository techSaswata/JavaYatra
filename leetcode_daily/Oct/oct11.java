package leetcode_daily.Oct;
// lc 2186
import java.util.*;
public class oct11 {
    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        ArrayList<Long> v = new ArrayList<>();
        ArrayList<Long> w = new ArrayList<>();
        for (int i = 0, n = power.length; i < n; ) {
            int j = i;
            long val = power[i];
            long sum = 0;
            while (j < n && power[j] == power[i]) { sum += power[j]; j++; }
            v.add(val);
            w.add(sum);
            i = j;
        }
        int m = v.size();
        long[] V = new long[m];
        long[] W = new long[m];
        for (int i = 0; i < m; i++) { V[i] = v.get(i); W[i] = w.get(i); }
        long[] d = new long[m];
        for (int i = 0; i < m; i++) {
            long take = W[i];
            long key = V[i] - 3;
            int idx = Arrays.binarySearch(V, 0, i, key);
            int j = idx >= 0 ? idx : -idx - 2;
            if (j >= 0) take += d[j];
            d[i] = Math.max(i > 0 ? d[i - 1] : 0L, take);
        }
        return d[m - 1];
    }
}



