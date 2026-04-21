class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int[] swap : allowedSwaps) {
            int rootI = find(swap[0], parent);
            int rootJ = find(swap[1], parent);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
        
        long[] s = new long[n];
        long[] t = new long[n];
        
        for (int i = 0; i < n; i++) {
            int root = find(i, parent);
            s[i] = ((long) root << 32) | source[i];
            t[i] = ((long) root << 32) | target[i];
        }
        
        java.util.Arrays.sort(s);
        java.util.Arrays.sort(t);
        
        int res = 0;
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else if (s[i] < t[j]) {
                i++;
                res++;
            } else {
                j++;
            }
        }
        
        return res + n - i;
    }
    
    private int find(int i, int[] parent) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}