class Solution {
    public int[] separateDigits(int[] nums) {
        int[] res = new int[nums.length * 6];
        int idx = 0;
        for (int num : nums) {
            if (num >= 100000) {
                res[idx++] = num / 100000;
                res[idx++] = (num / 10000) % 10;
                res[idx++] = (num / 1000) % 10;
                res[idx++] = (num / 100) % 10;
                res[idx++] = (num / 10) % 10;
                res[idx++] = num % 10;
            } else if (num >= 10000) {
                res[idx++] = num / 10000;
                res[idx++] = (num / 1000) % 10;
                res[idx++] = (num / 100) % 10;
                res[idx++] = (num / 10) % 10;
                res[idx++] = num % 10;
            } else if (num >= 1000) {
                res[idx++] = num / 1000;
                res[idx++] = (num / 100) % 10;
                res[idx++] = (num / 10) % 10;
                res[idx++] = num % 10;
            } else if (num >= 100) {
                res[idx++] = num / 100;
                res[idx++] = (num / 10) % 10;
                res[idx++] = num % 10;
            } else if (num >= 10) {
                res[idx++] = num / 10;
                res[idx++] = num % 10;
            } else {
                res[idx++] = num;
            }
        }
        int[] ans = new int[idx];
        System.arraycopy(res, 0, ans, 0, idx);
        return ans;
    }
}