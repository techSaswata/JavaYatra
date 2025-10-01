package leetcode_daily.Oct;
//lc 1518
class Solution {
    public int numWaterBottles(int a, int b) {
        int t = a, c = a;
        while (c >= b) {
            int sc = c / b;
            t += sc;
            c = sc + (c % b);
        }
        return t;
    }
}
