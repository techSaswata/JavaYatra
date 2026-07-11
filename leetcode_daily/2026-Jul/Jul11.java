class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        int[] edgesCount = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        for (int[] edge : edges) {
            int rootU = find(parent, edge[0]);
            int rootV = find(parent, edge[1]);
            
            if (rootU != rootV) {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
                edgesCount[rootU] += edgesCount[rootV] + 1;
            } else {
                edgesCount[rootU]++;
            }
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                if (edgesCount[i] == size[i] * (size[i] - 1) / 2) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}