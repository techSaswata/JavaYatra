class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] d = new int[26][26];
        int INF = 1000000000;
        
        for (int i = 0; i < 26; i++) {
            java.util.Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }
        
        int m = original.length;
        for (int i = 0; i < m; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            if (cost[i] < d[u][v]) {
                d[u][v] = cost[i];
            }
        }
        
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (d[i][k] == INF) continue;
                for (int j = 0; j < 26; j++) {
                    if (d[k][j] != INF) {
                        int newCost = d[i][k] + d[k][j];
                        if (newCost < d[i][j]) {
                            d[i][j] = newCost;
                        }
                    }
                }
            }
        }
        
        long totalCost = 0;
        int n = source.length();
        for (int i = 0; i < n; i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';
            if (u != v) {
                if (d[u][v] == INF) return -1;
                totalCost += d[u][v];
            }
        }
        
        return totalCost;
    }
}