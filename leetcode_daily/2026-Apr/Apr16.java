class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }
        
        int[] head = new int[maxVal + 1];
        int[] tail = new int[maxVal + 1];
        int[] count = new int[maxVal + 1];
        int[] next_idx = new int[n];
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            if (count[val] == 0) {
                head[val] = i;
                tail[val] = i;
            } else {
                next_idx[tail[val]] = i;
                tail[val] = i;
            }
            count[val]++;
        }
        
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = -1;
        }
        
        for (int val = 0; val <= maxVal; val++) {
            if (count[val] > 1) {
                int first = head[val];
                int last = tail[val];
                int curr = first;
                int prev = last;
                
                for (int step = 0; step < count[val]; step++) {
                    int nxt = next_idx[curr];
                    int next_node = (step == count[val] - 1) ? first : nxt;
                    
                    int dist_prev = curr - prev;
                    if (dist_prev < 0) {
                        dist_prev += n;
                    }
                    
                    int dist_next = next_node - curr;
                    if (dist_next < 0) {
                        dist_next += n;
                    }
                    
                    ans[curr] = dist_prev < dist_next ? dist_prev : dist_next;
                    
                    prev = curr;
                    curr = nxt;
                }
            }
        }
        
        java.util.List<Integer> result = new java.util.ArrayList<>(queries.length);
        for (int i = 0; i < queries.length; i++) {
            result.add(ans[queries[i]]);
        }
        
        return result;
    }
}