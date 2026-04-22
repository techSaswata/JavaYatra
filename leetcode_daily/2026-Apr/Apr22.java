class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new java.util.ArrayList<>();
        if (queries == null || queries.length == 0) {
            return res;
        }
        int n = queries[0].length();
        for (String q : queries) {
            for (String d : dictionary) {
                int diff = 0;
                for (int i = 0; i < n; i++) {
                    if (q.charAt(i) != d.charAt(i)) {
                        diff++;
                        if (diff > 2) {
                            break;
                        }
                    }
                }
                if (diff <= 2) {
                    res.add(q);
                    break;
                }
            }
        }
        return res;
    }
}