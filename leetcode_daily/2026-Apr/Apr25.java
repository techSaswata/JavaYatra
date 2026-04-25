class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] a = new long[2 * n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (y == 0) {
                a[i] = x;
            } else if (x == side) {
                a[i] = side + y;
            } else if (y == side) {
                a[i] = 2L * side + (side - x);
            } else {
                a[i] = 3L * side + (side - y);
            }
        }
        
        java.util.Arrays.sort(a, 0, n);
        
        long perimeter = 4L * side;
        for (int i = 0; i < n; i++) {
            a[i + n] = a[i] + perimeter;
        }
        
        int[] nxt = new int[2 * n + 1];
        long low = 1, high = side;
        long ans = 1;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(mid, a, n, k, perimeter, nxt)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return (int) ans;
    }
    
    private boolean check(long d, long[] a, int n, int k, long perimeter, int[] nxt) {
        if (k * d > perimeter) return false;
        
        int j = 0;
        int doubleN = 2 * n;
        for (int i = 0; i < doubleN; i++) {
            while (j < doubleN && a[j] - a[i] < d) {
                j++;
            }
            nxt[i] = j;
        }
        nxt[doubleN] = doubleN;
        
        for (int i = 0; i < n; i++) {
            int curr = i;
            for (int step = 0; step < k; step++) {
                curr = nxt[curr];
                if (curr > i + n) break; 
            }
            if (curr <= i + n) {
                return true;
            }
        }
        return false;
    }
}