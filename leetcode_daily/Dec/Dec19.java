import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        boolean[] known = new boolean[n];
        known[0] = true;
        known[firstPerson] = true;

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] visitedStep = new int[n];
        int step = 1;
        
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> uniqueNodes = new ArrayList<>();

        int m = meetings.length;
        int i = 0;

        while (i < m) {
            int time = meetings[i][2];
            uniqueNodes.clear();
            
            int j = i;
            while (j < m && meetings[j][2] == time) {
                int u = meetings[j][0];
                int v = meetings[j][1];
                
                graph[u].add(v);
                graph[v].add(u);
                
                if (visitedStep[u] != step) {
                    visitedStep[u] = step;
                    uniqueNodes.add(u);
                }
                if (visitedStep[v] != step) {
                    visitedStep[v] = step;
                    uniqueNodes.add(v);
                }
                j++;
            }
            
            for (int node : uniqueNodes) {
                if (known[node]) {
                    queue.offer(node);
                }
            }
            
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : graph[u]) {
                    if (!known[v]) {
                        known[v] = true;
                        queue.offer(v);
                    }
                }
            }
            
            for (int node : uniqueNodes) {
                graph[node].clear();
            }
            
            step++;
            i = j;
        }

        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (known[k]) {
                result.add(k);
            }
        }
        return result;
    }
}