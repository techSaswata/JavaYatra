class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        char[] res = new char[words.length];
        for (int i = 0; i < words.length; i++) {
            int sum = 0;
            String word = words[i];
            int len = word.length();
            for (int j = 0; j < len; j++) {
                sum += weights[word.charAt(j) - 'a'];
            }
            res[i] = (char) ('z' - (sum % 26));
        }
        return new String(res);
    }
}