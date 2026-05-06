class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] ans = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(ans[i], '.');
        }
        
        for (int i = 0; i < m; i++) {
            int empty = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (boxGrid[i][j] == '*') {
                    ans[j][m - 1 - i] = '*';
                    empty = j - 1;
                } else if (boxGrid[i][j] == '#') {
                    ans[empty][m - 1 - i] = '#';
                    empty--;
                }
            }
        }
        
        return ans;
    }
}