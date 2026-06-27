class Solution {
    public int maximumLength(int[] nums) {
        int capacity = 1 << 18;
        int mask = capacity - 1;
        int[] keys = new int[capacity];
        int[] counts = new int[capacity];
        
        int ones = 0;
        for (int num : nums) {
            if (num == 1) {
                ones++;
                continue;
            }
            int pos = hash(num) & mask;
            while (keys[pos] != 0 && keys[pos] != num) {
                pos = (pos + 1) & mask;
            }
            keys[pos] = num;
            counts[pos]++;
        }
        
        int maxLen = 1;
        if (ones > 0) {
            maxLen = ones % 2 == 0 ? ones - 1 : ones;
        }
        
        for (int i = 0; i < capacity; i++) {
            int x = keys[i];
            if (x == 0 || counts[i] < 2) continue;
            
            long curr = x;
            int len = 0;
            
            while (curr <= 1000000000) {
                int pos = hash((int)curr) & mask;
                while (keys[pos] != 0 && keys[pos] != (int)curr) {
                    pos = (pos + 1) & mask;
                }
                if (keys[pos] == (int)curr && counts[pos] >= 2) {
                    len += 2;
                    curr = curr * curr;
                } else {
                    break;
                }
            }
            
            if (curr <= 1000000000) {
                int pos = hash((int)curr) & mask;
                while (keys[pos] != 0 && keys[pos] != (int)curr) {
                    pos = (pos + 1) & mask;
                }
                if (keys[pos] == (int)curr) {
                    len += 1;
                } else {
                    len -= 1;
                }
            } else {
                len -= 1;
            }
            
            if (len > maxLen) maxLen = len;
        }
        
        return maxLen;
    }
    
    private int hash(int x) {
        x ^= x >>> 16;
        x *= 0x85ebca6b;
        x ^= x >>> 13;
        x *= 0xc2b2ae35;
        x ^= x >>> 16;
        return x;
    }
}