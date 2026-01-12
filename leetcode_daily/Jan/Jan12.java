class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;
        int x = points[0][0];
        int y = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int nextX = points[i][0];
            int nextY = points[i][1];
            time += Math.max(Math.abs(nextX - x), Math.abs(nextY - y));
            x = nextX;
            y = nextY;
        }
        return time;
    }
}