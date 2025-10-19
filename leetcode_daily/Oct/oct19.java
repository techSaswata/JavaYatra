package leetcode_daily.Oct;
// lc 1625
import java.util.*;
public class oct19 {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> x = new LinkedList<>();
        HashSet<String> y = new HashSet<>();
        String z = s;
        x.offer(s);
        y.add(s);
        while (!x.isEmpty()) {
            String c = x.poll();
            if (c.compareTo(z) < 0) z = c;
            char[] p = c.toCharArray();
            for (int i = 1; i < p.length; i += 2)
                p[i] = (char) (((p[i] - '0' + a) % 10) + '0');
            String q = new String(p);
            if (y.add(q)) x.offer(q);
            String r = c.substring(b) + c.substring(0, b);
            if (y.add(r)) x.offer(r);
        }
        return z;
    }
}
