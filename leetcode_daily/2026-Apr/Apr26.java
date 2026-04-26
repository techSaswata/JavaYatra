class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;
        int[] parent = new int[size];
        int[] rank = new int[size];
        
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                int u = i * n + j;
                
                if (j + 1 < n && grid[i][j + 1] == c) {
                    if (!union(u, u + 1, parent, rank)) {
                        return true;
                    }
                }
                
                if (i + 1 < m && grid[i + 1][j] == c) {
                    if (!union(u, u + n, parent, rank)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean union(int u, int v, int[] parent, int[] rank) {
        int rootU = u;
        while (rootU != parent[rootU]) {
            parent[rootU] = parent[parent[rootU]];
            rootU = parent[rootU];
        }
        
        int rootV = v;
        while (rootV != parent[rootV]) {
            parent[rootV] = parent[parent[rootV]];
            rootV = parent[rootV];
        }
        
        if (rootU == rootV) {
            return false;
        }
        
        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
        
        return true;
    }
}