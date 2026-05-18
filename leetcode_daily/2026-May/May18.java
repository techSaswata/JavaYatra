class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;
        if (arr[0] == arr[n - 1]) return 1;
        
        int hashSize = 131072;
        int[] headMap = new int[hashSize];
        int[] keys = new int[hashSize];
        java.util.Arrays.fill(headMap, -1);
        
        int[] head = new int[n];
        int[] next = new int[n];
        java.util.Arrays.fill(head, -1);
        
        int groupCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            int val = arr[i];
            int x = val;
            x = ((x >>> 16) ^ x) * 0x45d9f3b;
            x = ((x >>> 16) ^ x) * 0x45d9f3b;
            x = (x >>> 16) ^ x;
            int pos = x & (hashSize - 1);
            
            while (headMap[pos] != -1 && keys[pos] != val) {
                pos = (pos + 1) & (hashSize - 1);
            }
            if (headMap[pos] == -1) {
                keys[pos] = val;
                headMap[pos] = groupCount++;
            }
            int g = headMap[pos];
            next[i] = head[g];
            head[g] = i;
        }
        
        int[] q = new int[n];
        int qHead = 0, qTail = 0;
        boolean[] visited = new boolean[n];
        boolean[] groupVisited = new boolean[groupCount];
        
        q[qTail++] = 0;
        visited[0] = true;
        int steps = 0;
        
        while (qHead < qTail) {
            int size = qTail - qHead;
            for (int i = 0; i < size; i++) {
                int curr = q[qHead++];
                
                if (curr == n - 1) return steps;
                
                if (curr + 1 < n && !visited[curr + 1]) {
                    if (curr + 1 == n - 1) return steps + 1;
                    visited[curr + 1] = true;
                    q[qTail++] = curr + 1;
                }
                
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    q[qTail++] = curr - 1;
                }
                
                int val = arr[curr];
                int x = val;
                x = ((x >>> 16) ^ x) * 0x45d9f3b;
                x = ((x >>> 16) ^ x) * 0x45d9f3b;
                x = (x >>> 16) ^ x;
                int pos = x & (hashSize - 1);
                
                while (headMap[pos] != -1 && keys[pos] != val) {
                    pos = (pos + 1) & (hashSize - 1);
                }
                int g = headMap[pos];
                
                if (!groupVisited[g]) {
                    groupVisited[g] = true;
                    for (int nxt = head[g]; nxt != -1; nxt = next[nxt]) {
                        if (!visited[nxt]) {
                            if (nxt == n - 1) return steps + 1;
                            visited[nxt] = true;
                            q[qTail++] = nxt;
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}