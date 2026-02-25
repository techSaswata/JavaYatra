class Solution {
    public int[] sortByBits(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += Integer.bitCount(arr[i]) << 14;
        }
        java.util.Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] &= 16383;
        }
        return arr;
    }
}