class Solution {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        int rootVal = complexity[0];
        
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= rootVal) {
                return 0;
            }
        }
        
        long ans = 1;
        long mod = 1000000007L;
        
        for (int i = 1; i < n; i++) {
            ans = (ans * i) % mod;
        }
        
        return (int) ans;
    }
}