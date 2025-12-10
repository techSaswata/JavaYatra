class Solution {
    public int countPermutations(int[] complexity) {
        long ans = 1;
        int n = complexity.length;
        int rootComplexity = complexity[0];
        
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= rootComplexity) {
                return 0;
            }
            ans = (ans * i) % 1000000007L;
        }
        
        return (int) ans;
    }
}