import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k = k % total;
        
        int start = (total - k) % total;
        int r = start / n;
        int c = start % n;
        
        List<List<Integer>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                row.add(grid[r][c]);
                c++;
                if (c == n) {
                    c = 0;
                    r++;
                    if (r == m) {
                        r = 0;
                    }
                }
            }
            result.add(row);
        }
        
        return result;
    }
}