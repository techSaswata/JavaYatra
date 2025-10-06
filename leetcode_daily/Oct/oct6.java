package leetcode_daily.Oct;

// lc 778
import java.util.*;

public class oct6 {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dist[0][0] = grid[0][0];
        pq.offer(new int[]{grid[0][0], 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int t = cur[0], x = cur[1], y = cur[2];
            if (t != dist[x][y]) continue;
            if (x == n-1 && y == n-1) return t;
            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                int nt = Math.max(t, grid[nx][ny]);
                if (nt < dist[nx][ny]) {
                    dist[nx][ny] = nt;
                    pq.offer(new int[]{nt, nx, ny});
                }
            }
        }
        return -1;
    }
}

