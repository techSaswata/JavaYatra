class Solution {
    public int minimumDeletions(String s) {
        int b = 0, ans = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'b') {
                b++;
            } else if (b > 0) {
                ans++;
                if (ans > b) ans = b;
            }
        }
        return ans;
    }
}