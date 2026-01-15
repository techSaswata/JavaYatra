import java.util.Arrays;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int hGap = getMaxConsecutiveCount(hBars) + 1;
        int vGap = getMaxConsecutiveCount(vBars) + 1;
        int side = Math.min(hGap, vGap);
        return side * side;
    }

    private int getMaxConsecutiveCount(int[] bars) {
        Arrays.sort(bars);
        int maxLen = 1;
        int currentLen = 1;
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                currentLen++;
            } else {
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                }
                currentLen = 1;
            }
        }
        return currentLen > maxLen ? currentLen : maxLen;
    }
}