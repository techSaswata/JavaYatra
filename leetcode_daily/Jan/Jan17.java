import java.util.Arrays;

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        int[][] rects = new int[n][4];
        for (int i = 0; i < n; i++) {
            rects[i][0] = bottomLeft[i][0];
            rects[i][1] = bottomLeft[i][1];
            rects[i][2] = topRight[i][0];
            rects[i][3] = topRight[i][1];
        }
        
        Arrays.sort(rects, (a, b) -> Integer.compare(a[0], b[0]));
        
        int maxSide = 0;
        
        for (int i = 0; i < n; i++) {
            int[] r1 = rects[i];
            long w1 = (long)r1[2] - r1[0];
            long h1 = (long)r1[3] - r1[1];
            
            if (w1 <= maxSide || h1 <= maxSide) continue;
            
            for (int j = i + 1; j < n; j++) {
                int[] r2 = rects[j];
                
                if (r2[0] >= r1[2] - maxSide) break;
                
                int y1 = Math.max(r1[1], r2[1]);
                int y2 = Math.min(r1[3], r2[3]);
                
                if (y2 - y1 <= maxSide) continue;
                
                int x2 = Math.min(r1[2], r2[2]);
                int w = x2 - r2[0];
                
                if (w <= maxSide) continue;
                
                int h = y2 - y1;
                int side = Math.min(w, h);
                
                if (side > maxSide) {
                    maxSide = side;
                }
            }
        }
        
        return (long) maxSide * maxSide;
    }
}