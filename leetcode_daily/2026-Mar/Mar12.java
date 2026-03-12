class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int comps = n;
        int minMand = Integer.MAX_VALUE;
        
        for (int[] e : edges) {
            if (e[3] == 1) {
                minMand = Math.min(minMand, e[2]);
                int u = find(e[0], parent);
                int v = find(e[1], parent);
                if (u == v) return -1;
                parent[u] = v;
                comps--;
            }
        }
        
        for (int[] e : edges) {
            if (e[3] == 0) {
                int u = find(e[0], parent);
                int v = find(e[1], parent);
                if (u != v) {
                    parent[u] = v;
                    comps--;
                }
            }
        }
        
        if (comps > 1) return -1;
        
        int low = 1, high = Math.min(200000, minMand);
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, n, edges, k, parent)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean check(int X, int n, int[][] edges, int k, int[] parent) {
        for (int i = 0; i < n; i++) parent[i] = i;
        int comps = n;
        
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < X) return false;
                int u = find(e[0], parent);
                int v = find(e[1], parent);
                if (u != v) {
                    parent[u] = v;
                    comps--;
                }
            }
        }
        
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= X) {
                int u = find(e[0], parent);
                int v = find(e[1], parent);
                if (u != v) {
                    parent[u] = v;
                    comps--;
                }
            }
        }
        
        if (comps == 1) return true;
        
        int upgrades = 0;
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < X && e[2] * 2 >= X) {
                int u = find(e[0], parent);
                int v = find(e[1], parent);
                if (u != v) {
                    parent[u] = v;
                    comps--;
                    upgrades++;
                    if (upgrades > k) return false;
                    if (comps == 1) return true;
                }
            }
        }
        
        return false;
    }
    
    private int find(int i, int[] parent) {
        return parent[i] == i ? i : (parent[i] = find(parent[i], parent));
    }
}