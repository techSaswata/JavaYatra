class Solution {
    public int numberOfSubstrings(String s) {
        int l0 = -1, l1 = -1, l2 = -1;
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') l0 = i;
            else if (c == 'b') l1 = i;
            else l2 = i;
            
            int min = l0;
            if (l1 < min) min = l1;
            if (l2 < min) min = l2;
            
            ans += min + 1;
        }
        return ans;
    }
}