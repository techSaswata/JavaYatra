class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int n = nums1.length;
        int m = nums2.length;
        
        while (i < n && j < m) {
            int val1 = nums1[i];
            int val2 = nums2[j];
            
            if (val1 == val2) {
                return val1;
            } else if (val1 < val2) {
                i++;
            } else {
                j++;
            }
        }
        
        return -1;
    }
}