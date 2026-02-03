class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return false;
        int maxP = 0;
        while (maxP + 1 < n && nums[maxP] < nums[maxP + 1]) maxP++;
        if (maxP == 0) return false;
        int minQ = n - 1;
        while (minQ - 1 >= 0 && nums[minQ - 1] < nums[minQ]) minQ--;
        if (minQ == n - 1) return false;
        int i = 0;
        while (i < n - 1) {
            if (nums[i] > nums[i + 1]) {
                int L = i;
                int R = i;
                while (R + 1 < n && nums[R] > nums[R + 1]) R++;
                int pS = L > 1 ? L : 1;
                int pE = R < maxP ? R : maxP;
                if (pS <= pE) {
                    int qS = L > minQ ? L : minQ;
                    int qE = R < n - 2 ? R : n - 2;
                    if (qS <= qE && pS < qE) return true;
                }
                i = R;
            } else {
                i++;
            }
        }
        return false;
    }
}