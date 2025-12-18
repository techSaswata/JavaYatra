class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        int halfK = k >> 1;
        long baseProfit = 0;
        for (int i = 0; i < n; i++) {
            baseProfit += (long) strategy[i] * prices[i];
        }
        long currentDelta = 0;
        for (int i = 0; i < halfK; i++) {
            currentDelta -= (long) strategy[i] * prices[i];
        }
        for (int i = halfK; i < k; i++) {
            currentDelta += (long) prices[i] - (long) strategy[i] * prices[i];
        }
        long maxDelta = currentDelta > 0 ? currentDelta : 0;
        int limit = n - k;
        for (int i = 0; i < limit; i++) {
            currentDelta += (long) strategy[i] * prices[i];
            currentDelta -= prices[i + halfK];
            int idx = i + k;
            currentDelta += (long) prices[idx] - (long) strategy[idx] * prices[idx];
            if (currentDelta > maxDelta) {
                maxDelta = currentDelta;
            }
        }
        return baseProfit + maxDelta;
    }
}