class Solution {
    public int bestClosingTime(String customers) {
        int maxScore = 0;
        int score = 0;
        int bestHour = 0;
        int n = customers.length();
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                score++;
            } else {
                score--;
            }
            if (score > maxScore) {
                maxScore = score;
                bestHour = i + 1;
            }
        }
        return bestHour;
    }
}