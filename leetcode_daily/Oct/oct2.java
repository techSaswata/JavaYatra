package leetcode_daily.Oct;
//lc 3100
public class oct2 {
    public int maxBottlesDrunk(int a, int b) {
        int d = a, e = a, n = b;
        while (e >= n) {
            e = e - n + 1;
            d++;
            n++;
        }
        return d;
    }
}
