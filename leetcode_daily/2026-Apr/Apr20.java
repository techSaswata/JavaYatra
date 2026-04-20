class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int i = n - 1;
        while (colors[i] == colors[0]) {
            i--;
        }
        int res = i;
        i = 0;
        while (colors[i] == colors[n - 1]) {
            i++;
        }
        return Math.max(res, n - 1 - i);
    }
}