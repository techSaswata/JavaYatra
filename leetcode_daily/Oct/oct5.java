package leetcode_daily.Oct;
//lc 417
import java.util.*;

public class oct5 {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int c = 0; c < n; c++) {
            pac[c >= 0 ? 0 : 0][c] = true;
            q.offer(new int[]{0, c});
        }
        for (int r = 0; r < m; r++) {
            pac[r][0] = true;
            q.offer(new int[]{r, 0});
        }
        bfs(heights, q, pac);
        q.clear();
        for (int c = 0; c < n; c++) {
            atl[m - 1][c] = true;
            q.offer(new int[]{m - 1, c});
        }
        for (int r = 0; r < m; r++) {
            atl[r][n - 1] = true;
            q.offer(new int[]{r, n - 1});
        }
        bfs(heights, q, atl);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (pac[i][j] && atl[i][j]) res.add(Arrays.asList(i, j));
        return res;
    }

    private void bfs(int[][] heights, ArrayDeque<int[]> q, boolean[][] vis) {
        int m = heights.length, n = heights[0].length;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (vis[nr][nc]) continue;
                if (heights[nr][nc] < heights[r][c]) continue;
                vis[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
    }
}


