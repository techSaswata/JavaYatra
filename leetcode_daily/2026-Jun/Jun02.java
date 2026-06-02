class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            int lStart = landStartTime[i];
            int lDur = landDuration[i];
            int lFin = lStart + lDur;
            for (int j = 0; j < waterStartTime.length; j++) {
                int wStart = waterStartTime[j];
                int wDur = waterDuration[j];
                int wFin = wStart + wDur;
                int lw = (lFin > wStart ? lFin : wStart) + wDur;
                if (lw < ans) {
                    ans = lw;
                }
                int wl = (wFin > lStart ? wFin : lStart) + lDur;
                if (wl < ans) {
                    ans = wl;
                }
            }
        }
        return ans;
    }
}