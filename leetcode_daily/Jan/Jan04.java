class Solution {
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            int root = (int) Math.sqrt(num);
            if (root * root == num) continue;
            int div = 0;
            for (int i = 2; i <= root; i++) {
                if (num % i == 0) {
                    if (div == 0) {
                        div = i;
                    } else {
                        div = 0;
                        break;
                    }
                }
            }
            if (div > 0) {
                sum += 1 + num + div + (num / div);
            }
        }
        return sum;
    }
}