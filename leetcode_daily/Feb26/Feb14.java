class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] glass = new double[102];
        glass[0] = poured;
        for (int i = 0; i < query_row; i++) {
            for (int j = i; j >= 0; j--) {
                double excess = (glass[j] - 1.0) * 0.5;
                if (excess > 0) {
                    glass[j + 1] += excess;
                    glass[j] = excess;
                } else {
                    glass[j] = 0;
                }
            }
        }
        return Math.min(1.0, glass[query_glass]);
    }
}