class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        int[] head = new int[n];
        for (int i = 0; i < n; i++) {
            head[i] = -1;
        }
        int m = invocations.length;
        int[] next = new int[m];
        int[] to = new int[m];
        
        for (int i = 0; i < m; i++) {
            int u = invocations[i][0];
            int v = invocations[i][1];
            to[i] = v;
            next[i] = head[u];
            head[u] = i;
        }
        
        boolean[] suspicious = new boolean[n];
        int[] q = new int[n];
        int headQ = 0, tailQ = 0;
        
        suspicious[k] = true;
        q[tailQ++] = k;
        
        while (headQ < tailQ) {
            int u = q[headQ++];
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    q[tailQ++] = v;
                }
            }
        }
        
        boolean canRemove = true;
        for (int i = 0; i < m; i++) {
            if (!suspicious[invocations[i][0]] && suspicious[invocations[i][1]]) {
                canRemove = false;
                break;
            }
        }
        
        List<Integer> result;
        if (canRemove) {
            result = new java.util.ArrayList<>(n - tailQ);
            for (int i = 0; i < n; i++) {
                if (!suspicious[i]) {
                    result.add(i);
                }
            }
        } else {
            result = new java.util.ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
        }
        
        return result;
    }
}