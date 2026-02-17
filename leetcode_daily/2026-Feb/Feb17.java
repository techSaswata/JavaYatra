import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        if (turnedOn > 8) return result;

        for (int h = 0; h < 12; h++) {
            int hBits = Integer.bitCount(h);
            if (hBits > turnedOn) continue;
            int mBitsNeeded = turnedOn - hBits;
            if (mBitsNeeded > 5) continue;

            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(m) == mBitsNeeded) {
                    result.add(new StringBuilder()
                        .append(h)
                        .append(m < 10 ? ":0" : ":")
                        .append(m)
                        .toString());
                }
            }
        }
        return result;
    }
}