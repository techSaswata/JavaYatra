import java.util.*;

class Solution {
    private int[] map;
    private boolean[] memo;
    private int[] buffer;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        map = new int[36];
        for (String s : allowed) {
            map[(s.charAt(0) - 'A') * 6 + (s.charAt(1) - 'A')] |= (1 << (s.charAt(2) - 'A'));
        }
        memo = new boolean[1 << 19];
        int n = bottom.length();
        buffer = new int[32];
        for (int i = 0; i < n; i++) buffer[i] = bottom.charAt(i) - 'A';
        return solve(0, n);
    }

    private boolean solve(int offset, int len) {
        if (len == 1) return true;
        int key = 1;
        for (int i = 0; i < len; i++) key = (key << 3) | buffer[offset + i];
        if (memo[key]) return false;

        if (generate(offset, len, 0)) return true;

        memo[key] = true;
        return false;
    }

    private boolean generate(int offset, int len, int idx) {
        if (idx == len - 1) return solve(offset + len, len - 1);

        int mask = map[buffer[offset + idx] * 6 + buffer[offset + idx + 1]];
        int nextOffset = offset + len;

        while (mask != 0) {
            int val = Integer.numberOfTrailingZeros(mask);
            buffer[nextOffset + idx] = val;
            if (generate(offset, len, idx + 1)) return true;
            mask &= ~(1 << val);
        }
        return false;
    }
}