class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int initial1s = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '1') {
                initial1s++;
            }
        }
        
        int[] z = new int[n + 2];
        int[] o = new int[n + 2];
        int k = 0;
        int oCount = 0;
        
        int i = 0;
        while (i < n && arr[i] == '1') {
            i++;
        }
        
        while (i < n) {
            int count0 = 0;
            while (i < n && arr[i] == '0') {
                count0++;
                i++;
            }
            k++;
            z[k] = count0;
            
            if (i < n) {
                int count1 = 0;
                while (i < n && arr[i] == '1') {
                    count1++;
                    i++;
                }
                if (i < n) {
                    oCount++;
                    o[oCount] = count1;
                }
            }
        }
        
        int val1 = 0, idx1 = -1;
        int val2 = 0, idx2 = -1;
        int val3 = 0, idx3 = -1;
        
        for (int j = 1; j <= k; j++) {
            int v = z[j];
            if (v > val1) {
                val3 = val2; idx3 = idx2;
                val2 = val1; idx2 = idx1;
                val1 = v; idx1 = j;
            } else if (v > val2) {
                val3 = val2; idx3 = idx2;
                val2 = v; idx2 = j;
            } else if (v > val3) {
                val3 = v; idx3 = j;
            }
        }
        
        int maxGain = 0;
        for (int j = 1; j <= oCount; j++) {
            int merged = z[j] + o[j] + z[j+1];
            
            int maxOther = 0;
            if (idx1 != j && idx1 != j + 1) {
                maxOther = val1;
            } else if (idx2 != j && idx2 != j + 1) {
                maxOther = val2;
            } else {
                maxOther = val3;
            }
            
            int currentGain = Math.max(merged, maxOther) - o[j];
            if (currentGain > maxGain) {
                maxGain = currentGain;
            }
        }
        
        return initial1s + maxGain;
    }
}