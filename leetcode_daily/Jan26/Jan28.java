package leetcode_daily.Jan26;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        
        int[] minVal = new int[10005];
        int[] suffixMin = new int[10005];
        int[] dr = {0, 1};
        int[] dc = {1, 0};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (int t = 0; t <= k; t++) {
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (dist[r][c] != Integer.MAX_VALUE) {
                        pq.offer(new int[]{dist[r][c], r, c});
                    }
                }
            }
            
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int d = curr[0];
                int r = curr[1];
                int c = curr[2];
                
                if (d > dist[r][c]) continue;
                
                for (int i = 0; i < 2; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        int newDist = d + grid[nr][nc];
                        if (newDist < dist[nr][nc]) {
                            dist[nr][nc] = newDist;
                            pq.offer(new int[]{newDist, nr, nc});
                        }
                    }
                }
            }
            
            if (t < k) {
                Arrays.fill(minVal, Integer.MAX_VALUE);
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        if (dist[r][c] != Integer.MAX_VALUE) {
                            int v = grid[r][c];
                            if (dist[r][c] < minVal[v]) {
                                minVal[v] = dist[r][c];
                            }
                        }
                    }
                }
                
                int currMin = Integer.MAX_VALUE;
                for (int v = 10004; v >= 0; v--) {
                    if (minVal[v] < currMin) {
                        currMin = minVal[v];
                    }
                    suffixMin[v] = currMin;
                }
                
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        int v = grid[r][c];
                        if (suffixMin[v] < dist[r][c]) {
                            dist[r][c] = suffixMin[v];
                        }
                    }
                }
            }
        }
        
        return dist[m-1][n-1];
    }
}