class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid[0].length;
        int[] colDiff = new int[m];
        int[] colX = new int[m];
        int ans = 0;
        
        for (char[] row : grid) {
            int totalDiff = 0;
            int totalX = 0;
            for (int j = 0; j < m; j++) {
                char c = row[j];
                if (c == 'X') {
                    colDiff[j]++;
                    colX[j]++;
                } else if (c == 'Y') {
                    colDiff[j]--;
                }
                totalDiff += colDiff[j];
                totalX += colX[j];
                
                if (totalDiff == 0 && totalX > 0) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}