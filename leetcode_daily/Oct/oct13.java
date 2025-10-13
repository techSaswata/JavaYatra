package leetcode_daily.Oct;

//lc 2273
import java.util.*;
public class oct13 {
    public List<String> removeAnagrams(String[] words) {
        List<String> r = new ArrayList<>();
        String l = "";
        for (String w : words) {
            char[] c = w.toCharArray();
            Arrays.sort(c);
            String s = new String(c);
            if (r.isEmpty() || !s.equals(l)) {
                r.add(w);
                l = s;
            }
        }
        return r;
    }
}

