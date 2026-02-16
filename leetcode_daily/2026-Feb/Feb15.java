class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        char[] val = new char[Math.max(i, j) + 2];
        int k = val.length - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            val[k--] = (char)((sum & 1) + '0');
            carry = sum >> 1;
        }
        return new String(val, k + 1, val.length - 1 - k);
    }
}