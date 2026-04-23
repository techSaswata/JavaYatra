class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        
        int capacity = 262144;
        int mask = capacity - 1;
        int[] keys = new int[capacity];
        int[] counts = new int[capacity];
        long[] sums = new long[capacity];
        
        for (int i = 0; i < n; i++) {
            int key = nums[i];
            int pos = hash(key) & mask;
            while (counts[pos] != 0 && keys[pos] != key) {
                pos = (pos + 1) & mask;
            }
            keys[pos] = key;
            ans[i] += (long) counts[pos] * i - sums[pos];
            counts[pos]++;
            sums[pos] += i;
        }
        
        counts = new int[capacity];
        sums = new long[capacity];
        
        for (int i = n - 1; i >= 0; i--) {
            int key = nums[i];
            int pos = hash(key) & mask;
            while (counts[pos] != 0 && keys[pos] != key) {
                pos = (pos + 1) & mask;
            }
            keys[pos] = key;
            ans[i] += sums[pos] - (long) counts[pos] * i;
            counts[pos]++;
            sums[pos] += i;
        }
        
        return ans;
    }
    
    private int hash(int key) {
        key ^= (key >>> 16);
        key *= 0x85ebca6b;
        key ^= (key >>> 13);
        key *= 0xc2b2ae35;
        key ^= (key >>> 16);
        return key;
    }
}