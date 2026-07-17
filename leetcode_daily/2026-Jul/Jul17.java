class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        
        int[] freq = new int[max + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        long[] exact = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            long c = 0;
            for (int j = i; j <= max; j += i) {
                c += freq[j];
            }
            exact[i] = c * (c - 1) / 2;
        }
        
        for (int i = max; i >= 1; i--) {
            for (int j = i + i; j <= max; j += i) {
                exact[i] -= exact[j];
            }
        }
        
        for (int i = 1; i <= max; i++) {
            exact[i] += exact[i - 1];
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int low = 1, high = max, res = max;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (exact[mid] > q) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = res;
        }
        
        return ans;
    }
}