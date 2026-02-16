package leetcode_daily.Jan26;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int minCost(int n, int[][] edges) {
        int m = edges.length;
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] next = new int[m * 2];
        int[] to = new int[m * 2];
        int[] weight = new int[m * 2];
        int idx = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            to[idx] = v;
            weight[idx] = w;
            next[idx] = head[u];
            head[u] = idx++;

            to[idx] = u;
            weight[idx] = w * 2;
            next[idx] = head[v];
            head[v] = idx++;
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(0L);

        while (!pq.isEmpty()) {
            long curr = pq.poll();
            int d = (int) (curr >>> 32);
            int u = (int) curr;

            if (d > dist[u]) continue;
            if (u == n - 1) return d;

            for (int i = head[u]; i != -1; i = next[i]) {
                int v = to[i];
                int w = weight[i];
                int newDist = d + w;
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer((((long) newDist) << 32) | v);
                }
            }
        }

        return -1;
    }
}