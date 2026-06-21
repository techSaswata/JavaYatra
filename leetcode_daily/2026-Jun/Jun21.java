class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int max = 0;
        for (int c : costs) {
            if (c > max) {
                max = c;
            }
        }
        int[] count = new int[max + 1];
        for (int c : costs) {
            count[c]++;
        }
        int bought = 0;
        for (int i = 1; i <= max; i++) {
            if (count[i] > 0) {
                if (coins < i) {
                    break;
                }
                int canBuy = Math.min(count[i], coins / i);
                bought += canBuy;
                coins -= canBuy * i;
            }
        }
        return bought;
    }
}