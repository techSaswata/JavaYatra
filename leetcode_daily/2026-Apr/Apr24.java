class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int diff = 0, blanks = 0;
        for (int i = 0, n = moves.length(); i < n; i++) {
            char c = moves.charAt(i);
            if (c == 'L') diff--;
            else if (c == 'R') diff++;
            else blanks++;
        }
        return (diff < 0 ? -diff : diff) + blanks;
    }
}