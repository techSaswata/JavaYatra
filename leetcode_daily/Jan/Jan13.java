import java.util.Arrays;

class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        int n = squares.length;
        long[] events = new long[n * 2];
        for (int i = 0; i < n; i++) {
            long y = squares[i][1];
            long l = squares[i][2];
            totalArea += (double) l * l;
            events[2 * i] = (y << 32) | (l & 0xFFFFFFFFL);
            events[2 * i + 1] = ((y + l) << 32) | (-l & 0xFFFFFFFFL);
        }
        
        Arrays.sort(events);
        
        double target = totalArea / 2.0;
        double currentArea = 0;
        long currentWidth = 0;
        
        for (int i = 0; i < events.length - 1; i++) {
            currentWidth += (int) events[i];
            
            long y1 = events[i] >>> 32;
            long y2 = events[i + 1] >>> 32;
            
            if (y2 > y1) {
                double chunk = (double) currentWidth * (y2 - y1);
                if (currentArea + chunk >= target) {
                    return y1 + (target - currentArea) / currentWidth;
                }
                currentArea += chunk;
            }
        }
        return events[events.length - 1] >>> 32;
    }
}