import java.util.Arrays;

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        int[] minRow = new int[n + 1];
        int[] maxRow = new int[n + 1];
        int[] minCol = new int[n + 1];
        int[] maxCol = new int[n + 1];

        Arrays.fill(minRow, Integer.MAX_VALUE);
        Arrays.fill(maxRow, -1);
        Arrays.fill(minCol, Integer.MAX_VALUE);
        Arrays.fill(maxCol, -1);

        for (int i = 0; i < buildings.length; i++) {
            int r = buildings[i][0];
            int c = buildings[i][1];
            if (c < minCol[r]) minCol[r] = c;
            if (c > maxCol[r]) maxCol[r] = c;
            if (r < minRow[c]) minRow[c] = r;
            if (r > maxRow[c]) maxRow[c] = r;
        }

        int count = 0;
        for (int i = 0; i < buildings.length; i++) {
            int r = buildings[i][0];
            int c = buildings[i][1];
            if (c > minCol[r] && c < maxCol[r] && r > minRow[c] && r < maxRow[c]) {
                count++;
            }
        }
        return count;
    }
}