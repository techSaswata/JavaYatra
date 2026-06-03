class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minA = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            int a = landStartTime[i] + landDuration[i];
            if (a < minA) {
                minA = a;
            }
        }
        
        int minB = Integer.MAX_VALUE;
        for (int j = 0; j < waterStartTime.length; j++) {
            int b = waterStartTime[j] + waterDuration[j];
            if (b < minB) {
                minB = b;
            }
        }
        
        int ans1 = Integer.MAX_VALUE;
        for (int j = 0; j < waterStartTime.length; j++) {
            int finish = Math.max(minA, waterStartTime[j]) + waterDuration[j];
            if (finish < ans1) {
                ans1 = finish;
            }
        }
        
        int ans2 = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            int finish = Math.max(minB, landStartTime[i]) + landDuration[i];
            if (finish < ans2) {
                ans2 = finish;
            }
        }
        
        return Math.min(ans1, ans2);
    }
}