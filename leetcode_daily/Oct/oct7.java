package leetcode_daily.Oct;

// lc 1488
import java.util.*;
public class oct7 {
    public int[] avoidFlood(int[] r) {
        int n = r.length;
        int[] a = new int[n];
        TreeSet<Integer> dry = new TreeSet<>();
        Map<Integer, Integer> full = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (r[i] == 0) {
                dry.add(i);
                a[i] = 1;
            } else {
                a[i] = -1;
                if (full.containsKey(r[i])) {
                    Integer d = dry.higher(full.get(r[i]));
                    if (d == null) return new int[0];
                    a[d] = r[i];
                    dry.remove(d);
                }
                full.put(r[i], i);
            }
        }
        return a;
    }
}
