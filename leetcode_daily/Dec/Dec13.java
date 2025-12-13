import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String> electronics = new ArrayList<>();
        List<String> grocery = new ArrayList<>();
        List<String> pharmacy = new ArrayList<>();
        List<String> restaurant = new ArrayList<>();

        int n = code.length;
        for (int i = 0; i < n; i++) {
            if (!isActive[i]) {
                continue;
            }

            String b = businessLine[i];
            List<String> target = null;
            switch (b) {
                case "electronics": target = electronics; break;
                case "grocery": target = grocery; break;
                case "pharmacy": target = pharmacy; break;
                case "restaurant": target = restaurant; break;
                default: continue;
            }

            String c = code[i];
            int len = c.length();
            if (len == 0) {
                continue;
            }

            boolean isValid = true;
            for (int j = 0; j < len; j++) {
                char ch = c.charAt(j);
                if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == '_')) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                target.add(c);
            }
        }

        Collections.sort(electronics);
        Collections.sort(grocery);
        Collections.sort(pharmacy);
        Collections.sort(restaurant);

        List<String> result = new ArrayList<>(electronics.size() + grocery.size() + pharmacy.size() + restaurant.size());
        result.addAll(electronics);
        result.addAll(grocery);
        result.addAll(pharmacy);
        result.addAll(restaurant);

        return result;
    }
}