class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        int m = edges.length;
        if (m == 0) return -1;
        
        int[] head = new int[n];
        java.util.Arrays.fill(head, -1);
        int[] to = new int[m];
        int[] cost = new int[m];
        int[] next = new int[m];
        int[] inDegree = new int[n];
        int[] uniqueCosts = new int[m];
        
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int c = edges[i][2];
            to[i] = v;
            cost[i] = c;
            next[i] = head[u];
            head[u] = i;
            inDegree[v]++;
            uniqueCosts[i] = c;
        }
        
        int[] topo = new int[n];
        int topoIdx = 0;
        int[] q = new int[n];
        int headQ = 0, tailQ = 0;
        
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q[tailQ++] = i;
            }
        }
        
        while (headQ < tailQ) {
            int u = q[headQ++];
            topo[topoIdx++] = u;
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (--inDegree[v] == 0) {
                    q[tailQ++] = v;
                }
            }
        }
        
        java.util.Arrays.sort(uniqueCosts);
        int uniqueCount = 0;
        for (int i = 0; i < m; i++) {
            if (i == 0 || uniqueCosts[i] != uniqueCosts[i - 1]) {
                uniqueCosts[uniqueCount++] = uniqueCosts[i];
            }
        }
        
        int left = 0, right = uniqueCount - 1;
        int ans = -1;
        long[] dist = new long[n];
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int threshold = uniqueCosts[mid];
            
            java.util.Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0;
            
            for (int i = 0; i < n; i++) {
                int u = topo[i];
                if (dist[u] == Long.MAX_VALUE) continue;
                
                for (int e = head[u]; e != -1; e = next[e]) {
                    int v = to[e];
                    if (!online[v]) continue;
                    int c = cost[e];
                    if (c >= threshold) {
                        if (dist[u] + c < dist[v]) {
                            dist[v] = dist[u] + c;
                        }
                    }
                }
            }
            
            if (dist[n - 1] <= k) {
                ans = threshold;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return ans;
    }
}