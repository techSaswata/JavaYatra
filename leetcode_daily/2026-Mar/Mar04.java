class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        int[] colIndex = new int[m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                    colIndex[i] = j;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (rowCount[i] == 1 && colCount[colIndex[i]] == 1) {
                ans++;
            }
        }
        return ans;
    }
}