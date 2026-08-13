class Solution {
    private int[] max_len;
    private int[] pref_len;
    private int[] suff_len;
    private int[] length;
    private char[] pref_char;
    private char[] suff_char;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int size = 4 * n + 1;
        max_len = new int[size];
        pref_len = new int[size];
        suff_len = new int[size];
        length = new int[size];
        pref_char = new char[size];
        suff_char = new char[size];

        build(1, 0, n - 1, s);

        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = max_len[1];
        }
        return ans;
    }

    private void build(int node, int l, int r, String s) {
        length[node] = r - l + 1;
        if (l == r) {
            char c = s.charAt(l);
            max_len[node] = 1;
            pref_len[node] = 1;
            pref_char[node] = c;
            suff_len[node] = 1;
            suff_char[node] = c;
            return;
        }
        int mid = (l + r) >> 1;
        int left = node << 1;
        int right = left | 1;
        build(left, l, mid, s);
        build(right, mid + 1, r, s);
        pushUp(node, left, right);
    }

    private void pushUp(int node, int left, int right) {
        pref_char[node] = pref_char[left];
        pref_len[node] = pref_len[left];
        if (pref_len[left] == length[left] && pref_char[left] == pref_char[right]) {
            pref_len[node] += pref_len[right];
        }

        suff_char[node] = suff_char[right];
        suff_len[node] = suff_len[right];
        if (suff_len[right] == length[right] && suff_char[right] == suff_char[left]) {
            suff_len[node] += suff_len[left];
        }

        max_len[node] = max_len[left] >= max_len[right] ? max_len[left] : max_len[right];
        if (suff_char[left] == pref_char[right]) {
            int combined = suff_len[left] + pref_len[right];
            if (combined > max_len[node]) {
                max_len[node] = combined;
            }
        }
    }

    private void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            pref_char[node] = c;
            suff_char[node] = c;
            return;
        }
        int mid = (l + r) >> 1;
        int left = node << 1;
        int right = left | 1;
        if (idx <= mid) {
            update(left, l, mid, idx, c);
        } else {
            update(right, mid + 1, r, idx, c);
        }
        pushUp(node, left, right);
    }
}