class Solution {
    public int minimumCost(int[] cost) {
        int[] count = new int[101];
        for (int c : cost) {
            count[c]++;
        }
        int ans = 0;
        int bought = 0;
        for (int i = 100; i > 0; i--) {
            while (count[i]-- > 0) {
                if (++bought % 3 != 0) {
                    ans += i;
                }
            }
        }
        return ans;
    }
}