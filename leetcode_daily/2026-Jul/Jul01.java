class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;
        
        int n2 = n * n;
        int[] dist = new int[n2];
        int[] q = new int[n2];
        int head = 0, tail = 0;
        
        for (int r = 0; r < n; r++) {
            List<Integer> row = grid.get(r);
            for (int c = 0; c < n; c++) {
                int idx = r * n + c;
                if (row.get(c) == 1) {
                    dist[idx] = 0;
                    q[tail++] = idx;
                } else {
                    dist[idx] = -1;
                }
            }
        }
        
        while (head < tail) {
            int u = q[head++];
            int r = u / n;
            int c = u - r * n;
            int d = dist[u] + 1;
            
            if (r > 0) {
                int v = u - n;
                if (dist[v] == -1) {
                    dist[v] = d;
                    q[tail++] = v;
                }
            }
            if (r < n - 1) {
                int v = u + n;
                if (dist[v] == -1) {
                    dist[v] = d;
                    q[tail++] = v;
                }
            }
            if (c > 0) {
                int v = u - 1;
                if (dist[v] == -1) {
                    dist[v] = d;
                    q[tail++] = v;
                }
            }
            if (c < n - 1) {
                int v = u + 1;
                if (dist[v] == -1) {
                    dist[v] = d;
                    q[tail++] = v;
                }
            }
        }
        
        int l = 0, r_bound = dist[0] < dist[n2 - 1] ? dist[0] : dist[n2 - 1];
        int ans = 0;
        int[] visited = new int[n2];
        int version = 0;
        
        while (l <= r_bound) {
            int mid = (l + r_bound) >>> 1;
            
            version++;
            head = 0;
            tail = 0;
            q[tail++] = 0;
            visited[0] = version;
            boolean found = false;
            
            while (head < tail) {
                int u = q[head++];
                if (u == n2 - 1) {
                    found = true;
                    break;
                }
                int r = u / n;
                int c = u - r * n;
                
                if (r > 0) {
                    int v = u - n;
                    if (visited[v] != version && dist[v] >= mid) {
                        visited[v] = version;
                        q[tail++] = v;
                    }
                }
                if (r < n - 1) {
                    int v = u + n;
                    if (visited[v] != version && dist[v] >= mid) {
                        visited[v] = version;
                        q[tail++] = v;
                    }
                }
                if (c > 0) {
                    int v = u - 1;
                    if (visited[v] != version && dist[v] >= mid) {
                        visited[v] = version;
                        q[tail++] = v;
                    }
                }
                if (c < n - 1) {
                    int v = u + 1;
                    if (visited[v] != version && dist[v] >= mid) {
                        visited[v] = version;
                        q[tail++] = v;
                    }
                }
            }
            
            if (found) {
                ans = mid;
                l = mid + 1;
            } else {
                r_bound = mid - 1;
            }
        }
        
        return ans;
    }
}