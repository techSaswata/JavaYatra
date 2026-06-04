class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;
        for (int i = num1; i <= num2; i++) {
            int n = i;
            int prev = n % 10;
            n /= 10;
            if (n == 0) continue;
            int curr = n % 10;
            n /= 10;
            while (n > 0) {
                int next = n % 10;
                if ((curr > prev && curr > next) || (curr < prev && curr < next)) {
                    total++;
                }
                prev = curr;
                curr = next;
                n /= 10;
            }
        }
        return total;
    }
}