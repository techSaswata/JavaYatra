import java.util.*;

class Solution {
    private static class State {
        int len;
        BitSet sat;
        State(int len, BitSet sat) {
            this.len = len;
            this.sat = (BitSet) sat.clone();
        }
    }

    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        
        BitSet[] G = new BitSet[m];
        BitSet[] B = new BitSet[m];

        for (int j = 0; j < m; j++) {
            G[j] = new BitSet(n);
            B[j] = new BitSet(n);
            for (int r = 0; r < n - 1; r++) {
                char c1 = strs[r].charAt(j);
                char c2 = strs[r + 1].charAt(j);
                if (c1 < c2) G[j].set(r);
                else if (c1 > c2) B[j].set(r);
            }
        }

        List<State>[] dp = new ArrayList[m];
        for(int i=0; i<m; i++) dp[i] = new ArrayList<>();
        
        int maxLen = 0;

        for (int j = 0; j < m; j++) {
            List<State> candidates = new ArrayList<>();
            if (B[j].isEmpty()) {
                candidates.add(new State(1, G[j]));
            }

            for (int i = 0; i < j; i++) {
                for (State s : dp[i]) {
                    BitSet req = (BitSet) B[j].clone();
                    req.andNot(s.sat);
                    if (req.isEmpty()) {
                        BitSet nextSat = (BitSet) s.sat.clone();
                        nextSat.or(G[j]);
                        candidates.add(new State(s.len + 1, nextSat));
                    }
                }
            }

            for (State cand : candidates) {
                boolean dominated = false;
                for (State exist : dp[j]) {
                    if (exist.len >= cand.len) {
                        BitSet diff = (BitSet) cand.sat.clone();
                        diff.andNot(exist.sat);
                        if (diff.isEmpty()) {
                            dominated = true;
                            break;
                        }
                    }
                }
                if (!dominated) {
                    Iterator<State> it = dp[j].iterator();
                    while (it.hasNext()) {
                        State exist = it.next();
                        if (cand.len >= exist.len) {
                            BitSet diff = (BitSet) exist.sat.clone();
                            diff.andNot(cand.sat);
                            if (diff.isEmpty()) {
                                it.remove();
                            }
                        }
                    }
                    dp[j].add(cand);
                }
            }
            for(State s : dp[j]) maxLen = Math.max(maxLen, s.len);
        }
        return m - maxLen;
    }
}