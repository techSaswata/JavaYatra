package leetcode_daily.Oct;
//lc 407

import java.util.*;

public class oct3 {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        if (m == 0) return 0;
        int n = heightMap[0].length;
        if (m < 3 || n < 3) return 0;
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, 0, heightMap[i][0]});
            pq.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            vis[i][0] = true;
            vis[i][n - 1] = true;
        }
        for (int j = 1; j < n - 1; j++) {
            pq.offer(new int[]{0, j, heightMap[0][j]});
            pq.offer(new int[]{m - 1, j, heightMap[m - 1][j]});
            vis[0][j] = true;
            vis[m - 1][j] = true;
        }
        int res = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], h = cur[2];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || vis[nr][nc]) continue;
                vis[nr][nc] = true;
                int nh = heightMap[nr][nc];
                if (nh < h) {
                    res += h - nh;
                    pq.offer(new int[]{nr, nc, h});
                } else {
                    pq.offer(new int[]{nr, nc, nh});
                }
            }
        }
        return res;
    }
}



