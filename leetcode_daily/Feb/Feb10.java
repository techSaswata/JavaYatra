class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int max = 0;
        int[] seen = new int[100001];
        for (int i = 0; i < n; i++) {
            if (n - i <= max) break;
            int e = 0, o = 0;
            int mark = i + 1;
            for (int j = i; j < n; j++) {
                int v = nums[j];
                if (seen[v] != mark) {
                    seen[v] = mark;
                    if ((v & 1) == 0) e++;
                    else o++;
                }
                if (e == o) {
                    int len = j - i + 1;
                    if (len > max) max = len;
                }
            }
        }
        return max;
    }
}