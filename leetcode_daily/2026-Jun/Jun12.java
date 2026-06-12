class Solution {
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        int[] head = new int[n + 1];
        int[] to = new int[2 * n];
        int[] nxt = new int[2 * n];
        int edgeCount = 1;
        
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            to[edgeCount] = v;
            nxt[edgeCount] = head[u];
            head[u] = edgeCount++;
            
            to[edgeCount] = u;
            nxt[edgeCount] = head[v];
            head[v] = edgeCount++;
        }
        
        int[] up = new int[(n + 1) * 18];
        int[] depth = new int[n + 1];
        int[] q = new int[n];
        int headQ = 0, tailQ = 0;
        
        q[tailQ++] = 1;
        for (int i = 0; i < 18; i++) {
            up[18 + i] = 1;
        }
        
        while (headQ < tailQ) {
            int u = q[headQ++];
            for (int e = head[u]; e != 0; e = nxt[e]) {
                int v = to[e];
                if (v != up[u * 18]) {
                    depth[v] = depth[u] + 1;
                    up[v * 18] = u;
                    for (int i = 1; i < 18; i++) {
                        up[v * 18 + i] = up[up[v * 18 + i - 1] * 18 + i - 1];
                    }
                    q[tailQ++] = v;
                }
            }
        }
        
        int[] pow2 = new int[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] << 1);
            if (pow2[i] >= 1000000007) {
                pow2[i] -= 1000000007;
            }
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            if (u == v) {
                ans[i] = 0;
                continue;
            }
            
            int uNode = u;
            int vNode = v;
            
            if (depth[uNode] < depth[vNode]) {
                int temp = uNode; uNode = vNode; vNode = temp;
            }
            int diff = depth[uNode] - depth[vNode];
            for (int j = 0; j < 18; j++) {
                if (((diff >> j) & 1) == 1) {
                    uNode = up[uNode * 18 + j];
                }
            }
            int lca;
            if (uNode == vNode) {
                lca = uNode;
            } else {
                for (int j = 17; j >= 0; j--) {
                    if (up[uNode * 18 + j] != up[vNode * 18 + j]) {
                        uNode = up[uNode * 18 + j];
                        vNode = up[vNode * 18 + j];
                    }
                }
                lca = up[uNode * 18];
            }
            
            int dist = depth[u] + depth[v] - 2 * depth[lca];
            ans[i] = pow2[dist - 1];
        }
        
        return ans;
    }
}