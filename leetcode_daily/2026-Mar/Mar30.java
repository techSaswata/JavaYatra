class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int[] counts = new int[52];
        int i = 0;
        for (; i < n - 1; i += 2) {
            counts[s1.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
            counts[26 + s1.charAt(i + 1) - 'a']++;
            counts[26 + s2.charAt(i + 1) - 'a']--;
        }
        if (i < n) {
            counts[s1.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
        }
        for (int j = 0; j < 52; j++) {
            if (counts[j] != 0) {
                return false;
            }
        }
        return true;
    }
}