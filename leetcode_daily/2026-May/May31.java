class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        int max = 0;
        for (int a : asteroids) {
            if (a > max) {
                max = a;
            }
        }
        int[] counts = new int[max + 1];
        for (int a : asteroids) {
            counts[a]++;
        }
        long m = mass;
        for (int i = 1; i <= max; i++) {
            if (counts[i] > 0) {
                if (m < i) {
                    return false;
                }
                m += (long) i * counts[i];
            }
        }
        return true;
    }
}