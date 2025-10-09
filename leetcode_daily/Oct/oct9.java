package leetcode_daily.Oct;
//lc 3494
public class oct9 {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        long s = 0;
        long makespan = 0;
        long[] cur = new long[n];
        cur[0] = (long) skill[0] * mana[0];
        for (int i = 1; i < n; i++) cur[i] = cur[i - 1] + (long) skill[i] * mana[0];
        makespan = Math.max(makespan, s + cur[n - 1]);
        for (int j = 0; j + 1 < m; j++) {
            long[] next = new long[n];
            next[0] = (long) skill[0] * mana[j + 1];
            for (int i = 1; i < n; i++) next[i] = next[i - 1] + (long) skill[i] * mana[j + 1];
            long delta = Long.MIN_VALUE;
            for (int k = 0; k < n; k++) {
                long right = (k > 0) ? next[k - 1] : 0L;
                long val = cur[k] - right;
                if (val > delta) delta = val;
            }
            if (delta < 0) delta = 0;
            s += delta;
            makespan = Math.max(makespan, s + next[n - 1]);
            cur = next;
        }
        return makespan;
    }
}
