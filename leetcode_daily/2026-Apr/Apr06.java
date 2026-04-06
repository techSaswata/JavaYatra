class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int n = obstacles.length;
        int capacity = 2;
        while (capacity < n * 3) {
            capacity <<= 1;
        }
        int mask = capacity - 1;
        int shift = Integer.numberOfLeadingZeros(mask);
        long[] keys = new long[capacity];
        java.util.Arrays.fill(keys, Long.MIN_VALUE);

        for (int[] obs : obstacles) {
            long key = (((long) obs[0]) << 32) | (obs[1] & 0xFFFFFFFFL);
            int hash = (int) (key ^ (key >>> 32));
            int idx = (hash * 0x9E3779B9) >>> shift;
            while (keys[idx] != Long.MIN_VALUE) {
                if (keys[idx] == key) break;
                idx = (idx + 1) & mask;
            }
            keys[idx] = key;
        }

        int x = 0, y = 0;
        int dir = 0; 
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int maxDist = 0;

        for (int c : commands) {
            if (c == -2) {
                dir = (dir + 3) & 3;
            } else if (c == -1) {
                dir = (dir + 1) & 3;
            } else {
                for (int i = 0; i < c; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    
                    long key = (((long) nx) << 32) | (ny & 0xFFFFFFFFL);
                    int hash = (int) (key ^ (key >>> 32));
                    int idx = (hash * 0x9E3779B9) >>> shift;
                    
                    boolean blocked = false;
                    while (keys[idx] != Long.MIN_VALUE) {
                        if (keys[idx] == key) {
                            blocked = true;
                            break;
                        }
                        idx = (idx + 1) & mask;
                    }
                    if (blocked) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    int dist = x * x + y * y;
                    if (dist > maxDist) {
                        maxDist = dist;
                    }
                }
            }
        }

        return maxDist;
    }
}