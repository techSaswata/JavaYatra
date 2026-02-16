package leetcode_daily.Jan26;
import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();
        int maxNodes = 1;
        for (String s : original) maxNodes += s.length();
        for (String s : changed) maxNodes += s.length();
        
        int[][] trie = new int[maxNodes + 1][26];
        for (int[] row : trie) Arrays.fill(row, -1);
        int[] ids = new int[maxNodes + 1];
        Arrays.fill(ids, -1);
        int triePtr = 1;
        int stringCount = 0;

        for (String s : original) {
            int node = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                if (trie[node][c] == -1) trie[node][c] = triePtr++;
                node = trie[node][c];
            }
            if (ids[node] == -1) ids[node] = stringCount++;
        }
        for (String s : changed) {
            int node = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                if (trie[node][c] == -1) trie[node][c] = triePtr++;
                node = trie[node][c];
            }
            if (ids[node] == -1) ids[node] = stringCount++;
        }

        int K = stringCount;
        long[][] dist = new long[K][K];
        long INF = Long.MAX_VALUE / 2;
        for (long[] row : dist) Arrays.fill(row, INF);
        for (int i = 0; i < K; i++) dist[i][i] = 0;

        for (int i = 0; i < original.length; i++) {
            int u = getId(trie, ids, original[i]);
            int v = getId(trie, ids, changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        for (int k = 0; k < K; k++) {
            for (int i = 0; i < K; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < K; j++) {
                    if (dist[k][j] == INF) continue;
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;
            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            int nodeS = 0;
            int nodeT = 0;
            for (int j = i; j < n; j++) {
                int cS = source.charAt(j) - 'a';
                int cT = target.charAt(j) - 'a';
                if (trie[nodeS][cS] == -1 || trie[nodeT][cT] == -1) break;
                nodeS = trie[nodeS][cS];
                nodeT = trie[nodeT][cT];
                int u = ids[nodeS];
                int v = ids[nodeT];
                if (u != -1 && v != -1) {
                    if (dist[u][v] != INF) {
                        dp[j + 1] = Math.min(dp[j + 1], dp[i] + dist[u][v]);
                    }
                }
            }
        }

        return dp[n] == INF ? -1 : dp[n];
    }

    private int getId(int[][] trie, int[] ids, String s) {
        int node = 0;
        for (int i = 0; i < s.length(); i++) {
            node = trie[node][s.charAt(i) - 'a'];
        }
        return ids[node];
    }
}