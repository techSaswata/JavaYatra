class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0, res = 0;
        int n = nums1.length, m = nums2.length;
        
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                int dist = j - i;
                if (dist > res) {
                    res = dist;
                }
                j++;
            } else {
                i++;
                if (i > j) {
                    j = i;
                }
            }
        }
        
        return res;
    }
}