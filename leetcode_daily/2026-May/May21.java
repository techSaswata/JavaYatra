class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int[] trie = new int[5000000];
        int nodeCount = 1;
        int[] digits = new int[10];
        
        for (int val : arr1) {
            int len = 0;
            while (val > 0) {
                digits[len++] = val % 10;
                val /= 10;
            }
            int u = 0;
            for (int i = len - 1; i >= 0; i--) {
                int idx = u * 10 + digits[i];
                if (trie[idx] == 0) {
                    trie[idx] = nodeCount++;
                }
                u = trie[idx];
            }
        }
        
        int maxLen = 0;
        for (int val : arr2) {
            int len = 0;
            while (val > 0) {
                digits[len++] = val % 10;
                val /= 10;
            }
            int u = 0;
            int currentLen = 0;
            for (int i = len - 1; i >= 0; i--) {
                int idx = u * 10 + digits[i];
                if (trie[idx] == 0) {
                    break;
                }
                u = trie[idx];
                currentLen++;
            }
            if (currentLen > maxLen) {
                maxLen = currentLen;
            }
        }
        return maxLen;
    }
}