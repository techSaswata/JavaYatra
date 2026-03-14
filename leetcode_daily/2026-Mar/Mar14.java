class Solution {
    public String getHappyString(int n, int k) {
        int groupSize = 1 << (n - 1);
        if (k > 3 * groupSize) {
            return "";
        }
        char[] result = new char[n];
        int idx = (k - 1) / groupSize;
        result[0] = (char) ('a' + idx);
        k -= idx * groupSize;
        for (int i = 1; i < n; i++) {
            groupSize >>= 1;
            idx = (k - 1) / groupSize;
            char nextChar = (char) ('a' + idx);
            if (nextChar >= result[i - 1]) {
                nextChar++;
            }
            result[i] = nextChar;
            k -= idx * groupSize;
        }
        return new String(result);
    }
}