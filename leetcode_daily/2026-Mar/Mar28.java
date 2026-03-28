class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char curr = 'a';
        
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) {
                if (curr > 'z') return "";
                for (int j = i; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = curr;
                    }
                }
                curr++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (word[i] == word[j]) {
                    int expected = (i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] + 1 : 1;
                    if (lcp[i][j] != expected) {
                        return "";
                    }
                } else {
                    if (lcp[i][j] != 0) {
                        return "";
                    }
                }
            }
        }
        
        return new String(word);
    }
}