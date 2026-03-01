class Solution {
    public int minPartitions(String n) {
        char max = '0';
        for (int i = 0, len = n.length(); i < len; i++) {
            char c = n.charAt(i);
            if (c > max) {
                max = c;
                if (max == '9') return 9;
            }
        }
        return max - '0';
    }
}