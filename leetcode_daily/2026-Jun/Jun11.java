class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        int[] head = new int[n + 1];
        int[] to = new int[2 * n];
        int[] next = new int[2 * n];
        int edgeCount = 1;
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            to[edgeCount] = v;
            next[edgeCount] = head[u];
            head[u] = edgeCount++;
            
            to[edgeCount] = u;
            next[edgeCount] = head[v];
            head[v] = edgeCount++;
        }
        
        int[] q = new int[n];
        int[] parent = new int[n + 1];
        int headQ = 0, tailQ = 0;
        
        q[tailQ++] = 1;
        parent[1] = 0;
        
        int maxDepth = 0;
        while (headQ < tailQ) {
            int size = tailQ - headQ;
            for (int i = 0; i < size; i++) {
                int u = q[headQ++];
                for (int e = head[u]; e != 0; e = next[e]) {
                    int v = to[e];
                    if (v != parent[u]) {
                        parent[v] = u;
                        q[tailQ++] = v;
                    }
                }
            }
            if (headQ < tailQ) {
                maxDepth++;
            }
        }
        
        int mod = 1000000007;
        long ans = 1;
        long base = 2;
        int exp = maxDepth - 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans = (ans * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        
        return (int) ans;
    }
}