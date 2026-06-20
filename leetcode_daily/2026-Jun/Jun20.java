class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        long[] arr = new long[m + 2];
        for (int i = 0; i < m; i++) {
            arr[i] = ((long) restrictions[i][0] << 32) | (restrictions[i][1] & 0xFFFFFFFFL);
        }
        arr[m] = (1L << 32);
        arr[m + 1] = ((long) n << 32) | (n - 1);
        
        java.util.Arrays.sort(arr);
        
        int[] ids = new int[m + 2];
        int[] hs = new int[m + 2];
        for (int i = 0; i < m + 2; i++) {
            ids[i] = (int) (arr[i] >>> 32);
            hs[i] = (int) (arr[i] & 0xFFFFFFFFL);
        }
        
        for (int i = 1; i < m + 2; i++) {
            long newH = (long) hs[i - 1] + ids[i] - ids[i - 1];
            if (newH < hs[i]) {
                hs[i] = (int) newH;
            }
        }
        
        for (int i = m; i >= 0; i--) {
            long newH = (long) hs[i + 1] + ids[i + 1] - ids[i];
            if (newH < hs[i]) {
                hs[i] = (int) newH;
            }
        }
        
        int max = 0;
        for (int i = 1; i < m + 2; i++) {
            long h1 = hs[i - 1];
            long h2 = hs[i];
            long dist = ids[i] - ids[i - 1];
            int peak = (int) ((h1 + h2 + dist) / 2);
            if (peak > max) {
                max = peak;
            }
        }
        
        return max;
    }
}