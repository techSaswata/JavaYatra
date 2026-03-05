class Solution {
    public int minOperations(String s) {
        int c = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            c += (s.charAt(i) ^ i) & 1;
        }
        return c < n - c ? c : n - c;
    }
}