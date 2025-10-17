package leetcode_daily.Oct;
//lc 3003
import java.util.*;
public class oct17 {
    private String s;
    private int k;
    private int n;
    private Map<Long, Integer> memo;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        this.n = s.length();
        this.memo = new HashMap<>();

        if (k == 26) {
            return 1;
        }
        return solve(0, 1, 0) + 1;
    }

    private int solve(int i, int changeLeft, int mask) {
        if (i == n) {
            return 0;
        }
        long state = ((long)i << 27) | ((long)changeLeft << 26) | mask;
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        int charBit = 1 << (s.charAt(i) - 'a');
        int newMask = mask | charBit;
        int res;
        if (Integer.bitCount(newMask) > k) {
            res = 1 + solve(i + 1, changeLeft, charBit);
        } else {
            res = solve(i + 1, changeLeft, newMask);
        }
        if (changeLeft == 1) {
            res = Math.max(res, solve(i + 1, 0, mask));
            int originalCharBit = 1 << (s.charAt(i) - 'a');
            for(int bit = 0; bit < 26; bit++){
                 int newCharBit = 1 << bit;
                 if (newCharBit == originalCharBit) continue;
                 int changedMask = mask | newCharBit;
                 if (Integer.bitCount(changedMask) > k) {
                    res = Math.max(res, 1 + solve(i + 1, 0, newCharBit));
                } else {
                    res = Math.max(res, solve(i + 1, 0, changedMask));
                }
            }
        }

        memo.put(state, res);
        return res;
    }
}

