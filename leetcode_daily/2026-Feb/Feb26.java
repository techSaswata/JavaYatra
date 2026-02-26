class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) + carry == '1') {
                carry = 1;
                steps += 2;
            } else {
                steps++;
            }
        }
        return steps + carry;
    }
}