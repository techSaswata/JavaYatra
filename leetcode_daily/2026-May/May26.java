class Solution {
    public int numberOfSpecialChars(String word) {
        int lower = 0;
        int upper = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c >= 'a') {
                lower |= 1 << (c - 'a');
            } else {
                upper |= 1 << (c - 'A');
            }
        }
        return Integer.bitCount(lower & upper);
    }
}