import java.util.*;

class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) return s;
        List<String> list = new ArrayList<>();
        int count = 0, start = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') count++;
            else count--;
            
            if (count == 0) {
                list.add("1" + makeLargestSpecial(s.substring(start + 1, i)) + "0");
                start = i + 1;
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String str : list) sb.append(str);
        return sb.toString();
    }
}