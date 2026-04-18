class Solution {
    public int mirrorDistance(int n) {
        int o = n;
        int r = 0;
        while (n > 0) {
            r = r * 10 + n % 10;
            n /= 10;
        }
        int d = o - r;
        return d >= 0 ? d : -d;
    }
}