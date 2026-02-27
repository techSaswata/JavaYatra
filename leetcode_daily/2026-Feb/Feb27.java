import java.util.Arrays;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            }
        }
        if (zeros == 0) return 0;
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[zeros] = 0;
        
        int[] q = new int[n + 2];
        int head = 0, tail = 0;
        q[tail++] = zeros;
        
        int[][] parent = new int[2][n + 3];
        for (int i = 0; i <= n + 2; i++) {
            parent[0][i] = i;
            parent[1][i] = i;
        }
        
        int pStart = zeros & 1;
        parent[pStart][zeros] = find(parent[pStart], zeros + 2);
        
        while (head < tail) {
            int u = q[head++];
            int d = dist[u];
            
            int minV = Math.abs(u - k);
            int maxV = u + k;
            if (maxV > n) maxV = 2 * n - maxV;
            
            int targetP = (u + k) & 1;
            int curr = find(parent[targetP], minV);
            
            while (curr <= maxV) {
                dist[curr] = d + 1;
                if (curr == 0) return d + 1;
                q[tail++] = curr;
                parent[targetP][curr] = find(parent[targetP], curr + 2);
                curr = find(parent[targetP], curr);
            }
        }
        return -1;
    }
    
    private int find(int[] p, int i) {
        int root = i;
        while (p[root] != root) {
            root = p[root];
        }
        int curr = i;
        while (curr != root) {
            int next = p[curr];
            p[curr] = root;
            curr = next;
        }
        return root;
    }
}