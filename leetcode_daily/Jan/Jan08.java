class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] dp = new int[m];
        
        int v = nums1[0];
        int p = v * nums2[0];
        dp[0] = p;
        for (int j = 1; j < m; j++) {
            p = v * nums2[j];
            dp[j] = dp[j - 1] > p ? dp[j - 1] : p;
        }
        
        for (int i = 1; i < n; i++) {
            v = nums1[i];
            int prevDiag = dp[0];
            p = v * nums2[0];
            if (p > dp[0]) dp[0] = p;
            
            for (int j = 1; j < m; j++) {
                int temp = dp[j];
                p = v * nums2[j];
                int term1 = (prevDiag > 0 ? prevDiag : 0) + p;
                int term2 = dp[j] > dp[j - 1] ? dp[j] : dp[j - 1];
                dp[j] = term1 > term2 ? term1 : term2;
                prevDiag = temp;
            }
        }
        return dp[m - 1];
    }
}