class Solution {
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }
        int n = arr.length;
        boolean[] visited = new boolean[n];
        int[] q = new int[n];
        int head = 0, tail = 0;
        
        q[tail++] = start;
        visited[start] = true;
        
        while (head < tail) {
            int curr = q[head++];
            int step = arr[curr];
            
            int left = curr - step;
            if (left >= 0 && !visited[left]) {
                if (arr[left] == 0) {
                    return true;
                }
                visited[left] = true;
                q[tail++] = left;
            }
            
            int right = curr + step;
            if (right < n && !visited[right]) {
                if (arr[right] == 0) {
                    return true;
                }
                visited[right] = true;
                q[tail++] = right;
            }
        }
        
        return false;
    }
}