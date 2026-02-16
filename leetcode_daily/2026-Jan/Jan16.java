package leetcode_daily.Jan26;
import java.util.Arrays;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int[] h = new int[hFences.length + 2];
        h[0] = 1;
        h[1] = m;
        System.arraycopy(hFences, 0, h, 2, hFences.length);
        Arrays.sort(h);

        int[] v = new int[vFences.length + 2];
        v[0] = 1;
        v[1] = n;
        System.arraycopy(vFences, 0, v, 2, vFences.length);
        Arrays.sort(v);

        int hLen = h.length;
        int[] hDiffs = new int[hLen * (hLen - 1) / 2];
        int count = 0;
        for (int i = 0; i < hLen; i++) {
            for (int j = i + 1; j < hLen; j++) {
                hDiffs[count++] = h[j] - h[i];
            }
        }
        Arrays.sort(hDiffs);

        int vLen = v.length;
        int[] vDiffs = new int[vLen * (vLen - 1) / 2];
        count = 0;
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                vDiffs[count++] = v[j] - v[i];
            }
        }
        Arrays.sort(vDiffs);

        int i = hDiffs.length - 1;
        int j = vDiffs.length - 1;
        while (i >= 0 && j >= 0) {
            if (hDiffs[i] == vDiffs[j]) {
                long side = hDiffs[i];
                return (int) ((side * side) % 1000000007);
            } else if (hDiffs[i] > vDiffs[j]) {
                i--;
            } else {
                j--;
            }
        }

        return -1;
    }
}