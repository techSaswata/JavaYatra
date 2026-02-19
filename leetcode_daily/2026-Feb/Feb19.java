class Solution {
    public int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        int prev = 0;
        int i = 0;
        while (i < n) {
            int count = 0;
            char c = chars[i];
            while (i < n && chars[i] == c) {
                i++;
                count++;
            }
            ans += (prev < count ? prev : count);
            prev = count;
        }
        return ans;
    }
}