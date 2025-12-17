import java.util.Arrays;

class Solution {
    public long maximumProfit(int[] prices, int k) {
        if (prices.length < 2 || k == 0) return 0;
        int m = k + 1;
        long[] s0 = new long[m];
        long[] s1 = new long[m];
        long[] s2 = new long[m];
        long[] n0 = new long[m];
        long[] n1 = new long[m];
        long[] n2 = new long[m];
        
        long INF = 1_000_000_000_000_000L;
        Arrays.fill(s0, -INF);
        Arrays.fill(s1, -INF);
        Arrays.fill(s2, -INF);
        s0[0] = 0;
        
        for (int p : prices) {
            System.arraycopy(s0, 0, n0, 0, m);
            System.arraycopy(s1, 0, n1, 0, m);
            System.arraycopy(s2, 0, n2, 0, m);
            
            for (int j = 0; j < k; j++) {
                if (s0[j] > -INF) {
                    long b = s0[j] - p;
                    if (b > n1[j]) n1[j] = b;
                    
                    long s = s0[j] + p;
                    if (s > n2[j]) n2[j] = s;
                }
                
                if (s1[j] > -INF) {
                    long s = s1[j] + p;
                    if (s > n0[j + 1]) n0[j + 1] = s;
                }
                
                if (s2[j] > -INF) {
                    long b = s2[j] - p;
                    if (b > n0[j + 1]) n0[j + 1] = b;
                }
            }
            
            long[] t = s0; s0 = n0; n0 = t;
            t = s1; s1 = n1; n1 = t;
            t = s2; s2 = n2; n2 = t;
        }
        
        long ans = 0;
        for (long v : s0) if (v > ans) ans = v;
        return ans;
    }
}