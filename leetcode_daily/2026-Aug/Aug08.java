class Solution {
    public int[] validSequence(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int n = w1.length;
        int m = w2.length;
        
        int[] R = new int[m + 1];
        R[m] = n;
        int idx = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (idx >= 0 && w1[idx] != w2[j]) {
                idx--;
            }
            R[j] = idx;
            idx--;
        }
        
        int[] ans = new int[m];
        int last_i = -1;
        int C = 1;
        
        for (int j = 0; j < m; j++) {
            boolean found = false;
            for (int i = last_i + 1; i < n; i++) {
                if (w1[i] == w2[j]) {
                    ans[j] = i;
                    last_i = i;
                    found = true;
                    break;
                } else if (C == 1 && i + 1 <= R[j + 1]) {
                    ans[j] = i;
                    last_i = i;
                    C = 0;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return new int[0];
            }
        }
        
        return ans;
    }
}