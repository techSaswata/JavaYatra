class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int N = n + m - 1;
        
        char[] word = new char[N];
        boolean[] fixed = new boolean[N];
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != 0 && word[i + j] != str2.charAt(j)) {
                        return "";
                    }
                    word[i + j] = str2.charAt(j);
                    fixed[i + j] = true;
                }
            }
        }
        
        int[] head = new int[N];
        for (int i = 0; i < N; i++) {
            head[i] = -1;
        }
        int[] next = new int[n];
        int[] val = new int[n];
        int edgeCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean dangerous = true;
                int last_z = -1;
                for (int j = 0; j < m; j++) {
                    if (fixed[i + j]) {
                        if (word[i + j] != str2.charAt(j)) {
                            dangerous = false;
                            break;
                        }
                    } else {
                        last_z = i + j;
                    }
                }
                
                if (dangerous) {
                    if (last_z == -1) {
                        return "";
                    }
                    val[edgeCount] = i;
                    next[edgeCount] = head[last_z];
                    head[last_z] = edgeCount++;
                }
            }
        }
        
        boolean[] forbidden = new boolean[26];
        int k = 0;
        while (k < N && k >= 0) {
            if (fixed[k]) {
                k++;
                continue;
            }
            
            char startChar = (word[k] == 0) ? 'a' : (char) (word[k] + 1);
            
            for (int i = 0; i < 26; i++) {
                forbidden[i] = false;
            }
            
            if (head[k] != -1) {
                for (int e = head[k]; e != -1; e = next[e]) {
                    int i = val[e];
                    boolean match = true;
                    for (int j = 0; j < m; j++) {
                        if (i + j == k) continue;
                        if (word[i + j] != str2.charAt(j)) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        forbidden[str2.charAt(k - i) - 'a'] = true;
                    }
                }
            }
            
            boolean placed = false;
            for (char c = startChar; c <= 'z'; c++) {
                if (!forbidden[c - 'a']) {
                    word[k] = c;
                    placed = true;
                    break;
                }
            }
            
            if (placed) {
                k++;
            } else {
                word[k] = 0;
                k--;
                while (k >= 0 && fixed[k]) {
                    k--;
                }
            }
        }
        
        if (k < 0) {
            return "";
        }
        return new String(word);
    }
}