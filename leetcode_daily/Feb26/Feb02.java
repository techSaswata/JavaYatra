import java.util.Arrays;

class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int K = k - 2;
        
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int[] uniq = new int[n];
        int m = 0;
        if (n > 0) {
            uniq[0] = sorted[0];
            m = 1;
            for (int i = 1; i < n; i++) {
                if (sorted[i] != sorted[i - 1]) {
                    uniq[m++] = sorted[i];
                }
            }
        }
        
        int[] ranks = new int[n];
        for(int i = 0; i < n; i++) {
            ranks[i] = Arrays.binarySearch(uniq, 0, m, nums[i]) + 1;
        }
        
        long[] bitCount = new long[m + 1];
        long[] bitSum = new long[m + 1];
        
        long minCost = Long.MAX_VALUE;
        int currentSize = 0;
        
        int rightBound = Math.min(1 + dist, n - 1);
        for (int j = 2; j <= rightBound; j++) {
            int rank = ranks[j];
            for (int x = rank; x <= m; x += x & -x) {
                bitCount[x]++;
                bitSum[x] += nums[j];
            }
            currentSize++;
        }
        
        for (int i = 1; i <= n - k + 1; i++) {
            if (i > 1) {
                int rankRem = ranks[i];
                int valRem = nums[i];
                for (int x = rankRem; x <= m; x += x & -x) {
                    bitCount[x]--;
                    bitSum[x] -= valRem;
                }
                currentSize--;
                
                int nextIdx = i + dist;
                if (nextIdx < n) {
                    int rankAdd = ranks[nextIdx];
                    int valAdd = nums[nextIdx];
                    for (int x = rankAdd; x <= m; x += x & -x) {
                        bitCount[x]++;
                        bitSum[x] += valAdd;
                    }
                    currentSize++;
                }
            }
            
            if (currentSize >= K) {
                long currentSum = 0;
                if (K > 0) {
                    int idx = 0;
                    long currentCnt = 0;
                    long runningSum = 0;
                    for (int b = 17; b >= 0; b--) {
                        int nextIdx = idx + (1 << b);
                        if (nextIdx <= m && currentCnt + bitCount[nextIdx] < K) {
                            idx = nextIdx;
                            currentCnt += bitCount[idx];
                            runningSum += bitSum[idx];
                        }
                    }
                    currentSum = runningSum + (K - currentCnt) * uniq[idx];
                }
                long total = (long) nums[0] + nums[i] + currentSum;
                if (total < minCost) {
                    minCost = total;
                }
            }
        }
        return minCost;
    }
}