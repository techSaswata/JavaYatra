class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int mx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= mx) {
                mx = nums[i];
            } else {
                nums[i] = gcd(nums[i], mx);
            }
        }
        
        java.util.Arrays.sort(nums);
        
        long sum = 0;
        for (int i = 0; i < n / 2; i++) {
            sum += gcd(nums[i], nums[n - 1 - i]);
        }
        
        return sum;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}