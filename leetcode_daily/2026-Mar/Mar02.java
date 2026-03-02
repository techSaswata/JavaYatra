class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length, res = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int p = -1;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    p = j;
                    break;
                }
            }
            a[i] = p;
        }
        for (int i = 0; i < n; i++) {
            int k = i;
            while (k < n && a[k] > i) k++;
            if (k == n) return -1;
            res += k - i;
            int t = a[k];
            System.arraycopy(a, i, a, i + 1, k - i);
            a[i] = t;
        }
        return res;
    }
}