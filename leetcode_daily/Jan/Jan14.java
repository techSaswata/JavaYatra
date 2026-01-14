import java.util.Arrays;

class Solution {
    public double separateSquares(int[][] squares) {
        int n = squares.length;
        double[] xCoords = new double[2 * n];
        for (int i = 0; i < n; i++) {
            xCoords[2 * i] = squares[i][0];
            xCoords[2 * i + 1] = (double)squares[i][0] + squares[i][2];
        }
        Arrays.sort(xCoords);
        int m = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i == 0 || xCoords[i] != xCoords[i - 1]) {
                xCoords[m++] = xCoords[i];
            }
        }
        
        Event[] events = new Event[2 * n];
        for (int i = 0; i < n; i++) {
            double y1 = squares[i][1];
            double y2 = (double)squares[i][1] + squares[i][2];
            double x1 = squares[i][0];
            double x2 = (double)squares[i][0] + squares[i][2];
            
            int idx1 = Arrays.binarySearch(xCoords, 0, m, x1);
            int idx2 = Arrays.binarySearch(xCoords, 0, m, x2);
            
            events[2 * i] = new Event(y1, 1, idx1, idx2);
            events[2 * i + 1] = new Event(y2, -1, idx1, idx2);
        }
        Arrays.sort(events);
        
        int[] count = new int[4 * m];
        double[] len = new double[4 * m];
        
        double totalArea = 0;
        double prevY = events[0].y;
        
        for (Event e : events) {
            double currY = e.y;
            totalArea += (currY - prevY) * len[1];
            if (m > 1) update(1, 0, m - 2, e.x1, e.x2 - 1, e.type, count, len, xCoords);
            prevY = currY;
        }
        
        Arrays.fill(count, 0);
        Arrays.fill(len, 0.0);
        
        double target = totalArea / 2.0;
        double currentArea = 0;
        prevY = events[0].y;
        
        for (Event e : events) {
            double currY = e.y;
            double width = currY - prevY;
            double activeLen = len[1];
            double areaPart = width * activeLen;
            
            if (currentArea + areaPart >= target) {
                return activeLen > 1e-9 ? prevY + (target - currentArea) / activeLen : prevY;
            }
            
            currentArea += areaPart;
            if (m > 1) update(1, 0, m - 2, e.x1, e.x2 - 1, e.type, count, len, xCoords);
            prevY = currY;
        }
        
        return prevY;
    }
    
    private void update(int node, int start, int end, int l, int r, int val, 
                       int[] count, double[] len, double[] xCoords) {
        if (l > end || r < start) return;
        
        if (l <= start && end <= r) {
            count[node] += val;
        } else {
            int mid = (start + end) >> 1;
            update(node << 1, start, mid, l, r, val, count, len, xCoords);
            update((node << 1) | 1, mid + 1, end, l, r, val, count, len, xCoords);
        }
        
        if (count[node] > 0) {
            len[node] = xCoords[end + 1] - xCoords[start];
        } else if (start != end) {
            len[node] = len[node << 1] + len[(node << 1) | 1];
        } else {
            len[node] = 0.0;
        }
    }
    
    private static class Event implements Comparable<Event> {
        double y;
        int type;
        int x1, x2;
        
        public Event(double y, int type, int x1, int x2) {
            this.y = y;
            this.type = type;
            this.x1 = x1;
            this.x2 = x2;
        }
        
        @Override
        public int compareTo(Event other) {
            return Double.compare(this.y, other.y);
        }
    }
}