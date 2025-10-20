package leetcode_daily.Oct;
// lc 2011
public class oct20 {
        public int finalValueAfterOperations(String[] a) {
        int x = 0;
        for (String y : a) x += y.charAt(1) == '+' ? 1 : -1;
        return x;
    }
}
