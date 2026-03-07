class Solution {
    public int minFlips(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int diff = 0;
        int minOps = n;
        for (int i = 0; i < 2 * n; i++) {
            int r = i % n;
            char c = chars[r];
            if (c != ((i & 1) == 0 ? '0' : '1')) {
                diff++;
            }
            if (i >= n) {
                if (c != (((i - n) & 1) == 0 ? '0' : '1')) {
                    diff--;
                }
            }
            if (i >= n - 1) {
                minOps = Math.min(minOps, Math.min(diff, n - diff));
            }
        }
        return minOps;
    }
}