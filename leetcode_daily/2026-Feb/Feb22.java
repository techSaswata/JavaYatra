class Solution {
    public int binaryGap(int n) {
        int max = 0;
        n >>>= Integer.numberOfTrailingZeros(n) + 1;
        while (n > 0) {
            int k = Integer.numberOfTrailingZeros(n) + 1;
            if (k > max) max = k;
            n >>>= k;
        }
        return max;
    }
}