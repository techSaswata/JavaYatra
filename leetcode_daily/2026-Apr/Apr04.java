class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (n == 0) return "";
        int cols = n / rows;
        char[] res = new char[n];
        int idx = 0;
        int step = cols + 1;
        for (int c = 0; c < cols; c++) {
            for (int pos = c, r = 0; r < rows && c + r < cols; r++, pos += step) {
                res[idx++] = encodedText.charAt(pos);
            }
        }
        while (idx > 0 && res[idx - 1] == ' ') {
            idx--;
        }
        return new String(res, 0, idx);
    }
}