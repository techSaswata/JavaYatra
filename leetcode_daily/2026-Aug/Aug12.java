class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int capacity = n * 2;
        int size = 1;
        while (size < capacity) {
            size <<= 1;
        }
        int[] keys = new int[size];
        int[] values = new int[size];
        int mask = size - 1;
        
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            int key = nums[right];
            int idx = hash(key) & mask;
            while (keys[idx] != 0 && keys[idx] != key) {
                idx = (idx + 1) & mask;
            }
            if (keys[idx] == 0) {
                keys[idx] = key;
            }
            values[idx]++;
            
            if (values[idx] > k) {
                while (nums[left] != key) {
                    int leftKey = nums[left];
                    int leftIdx = hash(leftKey) & mask;
                    while (keys[leftIdx] != leftKey) {
                        leftIdx = (leftIdx + 1) & mask;
                    }
                    values[leftIdx]--;
                    left++;
                }
                values[idx]--;
                left++;
            }
            int len = right - left + 1;
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }
    
    private int hash(int h) {
        h ^= h >>> 16;
        h *= 0x85ebca6b;
        h ^= h >>> 13;
        h *= 0xc2b2ae35;
        h ^= h >>> 16;
        return h;
    }
}