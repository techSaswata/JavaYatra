class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }
        
        List<Integer> L_list = new ArrayList<>();
        List<Integer> R_list = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                L_list.add(start);
                R_list.add(i - 1);
            } else {
                i++;
            }
        }
        
        int M = L_list.size();
        int[] L = new int[M];
        int[] R = new int[M];
        int[] len = new int[M];
        for (int j = 0; j < M; j++) {
            L[j] = L_list.get(j);
            R[j] = R_list.get(j);
            len[j] = R[j] - L[j] + 1;
        }
        
        int[] log2 = new int[M + 1];
        if (M > 1) {
            for (int j = 2; j <= M; j++) {
                log2[j] = log2[j / 2] + 1;
            }
        }
        
        int K_st = 0;
        int[][] st = null;
        if (M > 1) {
            K_st = log2[M - 1] + 1;
            st = new int[K_st][M - 1];
            for (int j = 0; j < M - 1; j++) {
                st[0][j] = len[j] + len[j + 1];
            }
            for (int j = 1; j < K_st; j++) {
                for (int m = 0; m + (1 << j) <= M - 1; m++) {
                    st[j][m] = Math.max(st[j - 1][m], st[j - 1][m + (1 << (j - 1))]);
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            
            int u = -1;
            int low = 0, high = M - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (R[mid] >= l) {
                    u = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            
            if (u == -1 || L[u] > r) {
                ans.add(totalOnes);
                continue;
            }
            
            int v = -1;
            low = u; 
            high = M - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (L[mid] <= r) {
                    v = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            
            int k = v - u + 1;
            if (k <= 1) {
                ans.add(totalOnes);
            } else if (k == 2) {
                int z1 = R[u] - Math.max(l, L[u]) + 1;
                int z2 = Math.min(r, R[v]) - L[v] + 1;
                ans.add(totalOnes + z1 + z2);
            } else if (k == 3) {
                int z1 = R[u] - Math.max(l, L[u]) + 1;
                int z2 = len[u + 1];
                int z3 = Math.min(r, R[v]) - L[v] + 1;
                ans.add(totalOnes + Math.max(z1 + z2, z2 + z3));
            } else {
                int z1 = R[u] - Math.max(l, L[u]) + 1;
                int zk = Math.min(r, R[v]) - L[v] + 1;
                int gain1 = z1 + len[u + 1];
                int gain2 = len[v - 1] + zk;
                
                int qL = u + 1;
                int qR = v - 2;
                int j = log2[qR - qL + 1];
                int gain3 = Math.max(st[j][qL], st[j][qR - (1 << j) + 1]);
                
                int maxGain = Math.max(gain1, Math.max(gain2, gain3));
                ans.add(totalOnes + maxGain);
            }
        }
        
        return ans;
    }
}