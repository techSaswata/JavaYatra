class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0;
        int current = 0;
        for (int g : gain) {
            current += g;
            if (current > max) {
                max = current;
            }
        }
        return max;
    }
}