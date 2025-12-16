import java.util.*;

class Solution {
    private ArrayList<Integer>[] adj;
    private int[] present, future;
    private int budget;
    private static final int INF = 1000000000;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.present = present;
        this.future = future;
        this.budget = budget;
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] h : hierarchy) adj[h[0]].add(h[1]);

        int[][] res = dfs(1);
        int max = 0;
        for (int v : res[0]) max = Math.max(max, v);
        return max;
    }

    private int[][] dfs(int u) {
        int[] dp0 = new int[budget + 1];
        int[] dp1 = new int[budget + 1];
        Arrays.fill(dp0, -INF);
        Arrays.fill(dp1, -INF);
        dp0[0] = 0;
        dp1[0] = 0;

        int maxW0 = 0, maxW1 = 0;

        for (int v : adj[u]) {
            int[][] child = dfs(v);
            dp0 = merge(dp0, child[0], maxW0);
            dp1 = merge(dp1, child[1], maxW1);
            maxW0 = getLast(dp0);
            maxW1 = getLast(dp1);
        }

        int[] res0 = new int[budget + 1];
        int[] res1 = new int[budget + 1];
        Arrays.fill(res0, -INF);
        Arrays.fill(res1, -INF);

        int p = present[u - 1];
        int f = future[u - 1];
        int prof = f - p;
        int halfP = p / 2;
        int profHalf = f - halfP;

        for (int i = 0; i <= maxW0; i++) {
            if (dp0[i] > -INF) {
                res0[i] = Math.max(res0[i], dp0[i]);
                res1[i] = Math.max(res1[i], dp0[i]);
            }
        }

        if (p <= budget) {
            for (int i = 0; i <= maxW1; i++) {
                if (dp1[i] > -INF && i + p <= budget) {
                    res0[i + p] = Math.max(res0[i + p], dp1[i] + prof);
                }
            }
        }

        if (halfP <= budget) {
            for (int i = 0; i <= maxW1; i++) {
                if (dp1[i] > -INF && i + halfP <= budget) {
                    res1[i + halfP] = Math.max(res1[i + halfP], dp1[i] + profHalf);
                }
            }
        }
        return new int[][]{res0, res1};
    }

    private int[] merge(int[] a, int[] b, int maxA) {
        int[] next = new int[budget + 1];
        Arrays.fill(next, -INF);
        int maxB = getLast(b);
        for (int i = 0; i <= maxA; i++) {
            if (a[i] == -INF) continue;
            int lim = Math.min(budget - i, maxB);
            for (int j = 0; j <= lim; j++) {
                if (b[j] != -INF) {
                    next[i + j] = Math.max(next[i + j], a[i] + b[j]);
                }
            }
        }
        return next;
    }

    private int getLast(int[] a) {
        for (int i = budget; i >= 0; i--) if (a[i] > -INF) return i;
        return 0;
    }
}