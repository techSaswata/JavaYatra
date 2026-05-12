class Solution {
    public int minimumEffort(int[][] tasks) {
        int[] sumActuals = new int[10001];
        for (int[] task : tasks) {
            sumActuals[task[1] - task[0]] += task[0];
        }
        int ans = 0;
        for (int d = 0; d < 10001; d++) {
            if (sumActuals[d] > 0) {
                ans = sumActuals[d] + (d > ans ? d : ans);
            }
        }
        return ans;
    }
}