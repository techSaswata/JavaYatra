class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] has = new boolean[2048];
        int[] unique = new int[1501];
        int uniqueCount = 0;
        for (int x : nums) {
            if (!has[x]) {
                has[x] = true;
                unique[uniqueCount++] = x;
            }
        }
        
        boolean[] pair = new boolean[2048];
        int[] pairList = new int[2048];
        int pairCount = 0;
        for (int i = 0; i < uniqueCount; i++) {
            for (int j = i; j < uniqueCount; j++) {
                int p = unique[i] ^ unique[j];
                if (!pair[p]) {
                    pair[p] = true;
                    pairList[pairCount++] = p;
                }
            }
        }
        
        boolean[] triplet = new boolean[2048];
        int ans = 0;
        for (int i = 0; i < pairCount; i++) {
            int v = pairList[i];
            for (int j = 0; j < uniqueCount; j++) {
                int t = v ^ unique[j];
                if (!triplet[t]) {
                    triplet[t] = true;
                    ans++;
                }
            }
        }
        
        return ans;
    }
}