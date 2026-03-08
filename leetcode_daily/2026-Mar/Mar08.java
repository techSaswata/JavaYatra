class Solution {
    public String findDifferentBinaryString(String[] nums) {
        char[] result = new char[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = (char) (nums[i].charAt(i) ^ 1);
        }
        return new String(result);
    }
}