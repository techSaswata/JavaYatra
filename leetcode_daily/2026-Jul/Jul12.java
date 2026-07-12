class Solution {
    public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int[] sortedArr = arr.clone();
        java.util.Arrays.sort(sortedArr);
        int uniqueCount = 1;
        for (int i = 1; i < sortedArr.length; i++) {
            if (sortedArr[i] != sortedArr[i - 1]) {
                sortedArr[uniqueCount++] = sortedArr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = java.util.Arrays.binarySearch(sortedArr, 0, uniqueCount, arr[i]) + 1;
        }
        return arr;
    }
}