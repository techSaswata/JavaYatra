class Solution {
    public boolean hasAllCodes(String s, int k) {
        int len = s.length();
        int total = 1 << k;
        if (len - k + 1 < total) {
            return false;
        }
        boolean[] seen = new boolean[total];
        int count = 0;
        int hash = 0;
        int mask = total - 1;
        char[] val = s.toCharArray();
        for (int i = 0; i < k - 1; i++) {
            hash = (hash << 1) | (val[i] & 1);
        }
        for (int i = k - 1; i < len; i++) {
            hash = ((hash << 1) & mask) | (val[i] & 1);
            if (!seen[hash]) {
                seen[hash] = true;
                count++;
                if (count == total) {
                    return true;
                }
            }
        }
        return false;
    }
}