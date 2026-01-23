import java.util.PriorityQueue;

class Solution {
    private static class Entry implements Comparable<Entry> {
        long sum;
        int sortIndex;
        int u;
        int v;

        public Entry(long sum, int sortIndex, int u, int v) {
            this.sum = sum;
            this.sortIndex = sortIndex;
            this.u = u;
            this.v = v;
        }

        public int compareTo(Entry o) {
            int cmp = Long.compare(this.sum, o.sum);
            if (cmp != 0) return cmp;
            return Integer.compare(this.sortIndex, o.sortIndex);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int maxNodes = 2 * n;
        long[] val = new long[maxNodes];
        int[] prev = new int[maxNodes];
        int[] next = new int[maxNodes];
        int[] origIndex = new int[maxNodes];
        boolean[] removed = new boolean[maxNodes];

        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            origIndex[i] = i;
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        PriorityQueue<Entry> pq = new PriorityQueue<>();
        int inversionCount = 0;

        int curr = 0;
        while (curr != -1) {
            int nxt = next[curr];
            if (nxt != -1) {
                if (val[curr] > val[nxt]) {
                    inversionCount++;
                }
                pq.offer(new Entry(val[curr] + val[nxt], origIndex[curr], curr, nxt));
            }
            curr = nxt;
        }

        int ops = 0;
        int newNodeIdx = n;

        while (inversionCount > 0) {
            Entry e = pq.poll();
            if (e == null) break;

            int u = e.u;
            int v = e.v;

            if (removed[u] || removed[v] || next[u] != v) continue;

            int p = prev[u];
            int nxt = next[v];

            if (p != -1 && val[p] > val[u]) inversionCount--;
            if (val[u] > val[v]) inversionCount--;
            if (nxt != -1 && val[v] > val[nxt]) inversionCount--;

            int w = newNodeIdx++;
            val[w] = val[u] + val[v];
            origIndex[w] = origIndex[u];
            prev[w] = p;
            next[w] = nxt;
            removed[w] = false;

            removed[u] = true;
            removed[v] = true;

            if (p != -1) next[p] = w;
            if (nxt != -1) prev[nxt] = w;

            if (p != -1 && val[p] > val[w]) inversionCount++;
            if (nxt != -1 && val[w] > val[nxt]) inversionCount++;

            if (p != -1) {
                pq.offer(new Entry(val[p] + val[w], origIndex[p], p, w));
            }
            if (nxt != -1) {
                pq.offer(new Entry(val[w] + val[nxt], origIndex[w], w, nxt));
            }

            ops++;
        }

        return ops;
    }
}