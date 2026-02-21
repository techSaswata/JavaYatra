class Solution {
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            ans += (665772 >> Integer.bitCount(i)) & 1;
        }
        return ans;
    }
}