class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxVal) maxVal = nums[i];
        }
        
        int[] head = null, to = null, nxt = null;
        int[] spf = null;
        boolean[] primeUsed = null;
        
        if (maxVal >= 2) {
            spf = new int[maxVal + 1];
            for (int i = 2; i <= maxVal; i++) spf[i] = i;
            for (int i = 2; i * i <= maxVal; i++) {
                if (spf[i] == i) {
                    for (int j = i * i; j <= maxVal; j += i) {
                        if (spf[j] == j) {
                            spf[j] = i;
                        }
                    }
                }
            }
            
            head = new int[maxVal + 1];
            to = new int[n * 8 + 5];
            nxt = new int[n * 8 + 5];
            int edgeCount = 1;
            
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                int lastP = 0;
                while (x > 1) {
                    int p = spf[x];
                    if (p != lastP) {
                        to[edgeCount] = i;
                        nxt[edgeCount] = head[p];
                        head[p] = edgeCount++;
                        lastP = p;
                    }
                    x /= p;
                }
            }
            
            primeUsed = new boolean[maxVal + 1];
        }
        
        int[] q = new int[n];
        int headQ = 0, tailQ = 0;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = -1;
        
        q[tailQ++] = 0;
        dist[0] = 0;
        
        while (headQ < tailQ) {
            int u = q[headQ++];
            int d = dist[u];
            
            if (u + 1 < n && dist[u + 1] == -1) {
                if (u + 1 == n - 1) return d + 1;
                dist[u + 1] = d + 1;
                q[tailQ++] = u + 1;
            }
            
            if (u - 1 >= 0 && dist[u - 1] == -1) {
                dist[u - 1] = d + 1;
                q[tailQ++] = u - 1;
            }
            
            if (maxVal >= 2) {
                int val = nums[u];
                if (val > 1 && spf[val] == val) {
                    if (!primeUsed[val]) {
                        primeUsed[val] = true;
                        for (int e = head[val]; e != 0; e = nxt[e]) {
                            int v = to[e];
                            if (dist[v] == -1) {
                                if (v == n - 1) return d + 1;
                                dist[v] = d + 1;
                                q[tailQ++] = v;
                            }
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}