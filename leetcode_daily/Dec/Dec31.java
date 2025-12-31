class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = row * col;
        int[] root = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;
        int[] size = new int[n];
        int[] mask = new int[n];
        boolean[] grid = new boolean[n];
        int[] dirs = {0, 1, 0, -1, 0};

        for (int i = cells.length - 1; i >= 0; i--) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            int idx = r * col + c;

            grid[idx] = true;
            size[idx] = 1;
            int currentMask = 0;
            if (r == 0) currentMask |= 1;
            if (r == row - 1) currentMask |= 2;
            mask[idx] = currentMask;

            for (int d = 0; d < 4; d++) {
                int nr = r + dirs[d];
                int nc = c + dirs[d + 1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col) {
                    int nidx = nr * col + nc;
                    if (grid[nidx]) {
                        int r1 = find(root, idx);
                        int r2 = find(root, nidx);
                        if (r1 != r2) {
                            if (size[r1] > size[r2]) {
                                int temp = r1;
                                r1 = r2;
                                r2 = temp;
                            }
                            root[r1] = r2;
                            size[r2] += size[r1];
                            mask[r2] |= mask[r1];
                        }
                    }
                }
            }
            if (mask[find(root, idx)] == 3) return i;
        }
        return 0;
    }

    private int find(int[] root, int x) {
        int p = x;
        while (root[p] != p) {
            p = root[p];
        }
        while (x != p) {
            int next = root[x];
            root[x] = p;
            x = next;
        }
        return p;
    }
}