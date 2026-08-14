class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int maxLen = 0;
        int left = 0;
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            int c = chars[right] - 'a';
            count[c]++;
            while (count[c] > 2) {
                count[chars[left] - 'a']--;
                left++;
            }
            int len = right - left + 1;
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }
}