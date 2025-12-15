class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long total = 1;
        long currentLength = 1;
        for (int i = 1; i < n; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            total += currentLength;
        }
        return total;
    }
}