class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        int[][] g = new int[m][n];
        for (int i = 0; i < m; i++) {
            List<Integer> row = grid.get(i);
            for (int j = 0; j < n; j++) {
                g[i][j] = row.get(j);
            }
        }
        
        int startCost = g[0][0];
        if (startCost >= health) return false;
        if (m == 1 && n == 1) return true;
        
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        int[] q = new int[20000];
        int head = 10000, tail = 10000;
        
        dist[0][0] = startCost;
        q[tail++] = 0;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (head < tail) {
            int curr = q[head++];
            int r = curr / n;
            int c = curr % n;
            
            int d = dist[r][c];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = g[nr][nc];
                    int nd = d + weight;
                    if (nd < dist[nr][nc] && nd < health) {
                        if (nr == m - 1 && nc == n - 1) return true;
                        dist[nr][nc] = nd;
                        if (weight == 0) {
                            q[--head] = nr * n + nc;
                        } else {
                            q[tail++] = nr * n + nc;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}