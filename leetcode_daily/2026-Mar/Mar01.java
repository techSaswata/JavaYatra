class Solution {
    public int minPartitions(String n) {
        char max = '0';
        int len = n.length();
        for (int i = 0; i < len; i++) {
            char c = n.charAt(i);
            if (c == '9') return 9;
            if (c > max) max = c;
        }
        return max - '0';
    }
}