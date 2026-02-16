class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int maxLen = 0;
        int currentLen = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                currentLen++;
            } else {
                currentLen = 1;
            }
            if (currentLen > maxLen) maxLen = currentLen;
        }
        maxLen = Math.max(maxLen, solve2(arr, 'a', 'b', 'c'));
        maxLen = Math.max(maxLen, solve2(arr, 'b', 'c', 'a'));
        maxLen = Math.max(maxLen, solve2(arr, 'a', 'c', 'b'));
        int[] count = new int[3];
        java.util.HashMap<Long, Integer> map = new java.util.HashMap<>();
        long initialKey = (((long) n) << 32) | n;
        map.put(initialKey, -1);
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'a') count[0]++;
            else if (arr[i] == 'b') count[1]++;
            else count[2]++;
            int d1 = count[0] - count[1] + n;
            int d2 = count[1] - count[2] + n;
            long key = (((long) d1) << 32) | d2;
            Integer prevIndex = map.get(key);
            if (prevIndex != null) {
                maxLen = Math.max(maxLen, i - prevIndex);
            } else {
                map.put(key, i);
            }
        }
        return maxLen;
    }
    private int solve2(char[] arr, char c1, char c2, char forbidden) {
        int max = 0;
        int n = arr.length;
        int[] seen = new int[2 * n + 1];
        java.util.Arrays.fill(seen, -2);
        int start = 0;
        int bal = 0;
        seen[n] = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == forbidden) {
                start = i + 1;
                bal = 0;
                seen[n] = i;
            } else {
                if (arr[i] == c1) bal++;
                else if (arr[i] == c2) bal--;
                int idx = bal + n;
                if (seen[idx] >= start - 1) {
                    max = Math.max(max, i - seen[idx]);
                } else {
                    seen[idx] = i;
                }
            }
        }
        return max;
    }
}