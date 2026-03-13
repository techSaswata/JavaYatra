class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        java.util.Arrays.sort(workerTimes);
        int n = workerTimes.length;
        int[] uniqueW = new int[n];
        int[] counts = new int[n];
        int k = 0;
        
        for (int w : workerTimes) {
            if (k > 0 && uniqueW[k - 1] == w) {
                counts[k - 1]++;
            } else {
                uniqueW[k] = w;
                counts[k] = 1;
                k++;
            }
        }
        
        long minW = uniqueW[0];
        long low = 1;
        long high = minW * (long) mountainHeight * (mountainHeight + 1) / 2;
        long ans = high;
        
        while (low <= high) {
            long mid = (low + high) >>> 1;
            long totalReduced = 0;
            boolean possible = false;
            
            for (int i = 0; i < k; i++) {
                long w = uniqueW[i];
                long x = (long) Math.sqrt((double) mid * 2.0 / w);
                
                while (w * ((x + 1) * (x + 2) / 2) <= mid) {
                    x++;
                }
                while (x > 0 && w * (x * (x + 1) / 2) > mid) {
                    x--;
                }
                
                totalReduced += x * counts[i];
                if (totalReduced >= mountainHeight) {
                    possible = true;
                    break;
                }
            }
            
            if (possible) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
}