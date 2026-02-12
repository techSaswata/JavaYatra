class Solution {
    public int longestBalanced(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int maxLen = 0;
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            if (n - i <= maxLen) break;
            for (int k = 0; k < 26; k++) counts[k] = 0;
            int distinct = 0;
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                int idx = chars[j] - 'a';
                if (counts[idx] == 0) {
                    distinct++;
                }
                counts[idx]++;
                if (counts[idx] > maxFreq) {
                    maxFreq = counts[idx];
                }
                int len = j - i + 1;
                if (distinct * maxFreq == len) {
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }
        return maxLen;
    }
}