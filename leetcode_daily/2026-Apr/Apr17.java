class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int[] keys = new int[524288];
        int[] vals = new int[524288];
        int mask = 524287;
        int min_dist = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            int pos = num;
            pos ^= (pos >>> 16);
            pos *= 0x85ebca6b;
            pos ^= (pos >>> 13);
            pos *= 0xc2b2ae35;
            pos ^= (pos >>> 16);
            pos &= mask;
            
            while (keys[pos] != 0) {
                if (keys[pos] == num) {
                    int dist = i - vals[pos];
                    if (dist < min_dist) {
                        min_dist = dist;
                        if (min_dist == 1) {
                            return 1;
                        }
                    }
                    break;
                }
                pos = (pos + 1) & mask;
            }
            
            int x = num;
            int rev = 0;
            while (x > 0) {
                rev = rev * 10 + x % 10;
                x /= 10;
            }
            
            pos = rev;
            pos ^= (pos >>> 16);
            pos *= 0x85ebca6b;
            pos ^= (pos >>> 13);
            pos *= 0xc2b2ae35;
            pos ^= (pos >>> 16);
            pos &= mask;
            
            while (keys[pos] != 0 && keys[pos] != rev) {
                pos = (pos + 1) & mask;
            }
            keys[pos] = rev;
            vals[pos] = i;
        }
        
        return min_dist == Integer.MAX_VALUE ? -1 : min_dist;
    }
}