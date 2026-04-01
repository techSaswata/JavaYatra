class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ((long) positions[i] << 32) | (long) i;
        }
        Arrays.sort(arr);
        
        int[] stack = new int[n];
        int top = -1;
        char[] dirs = directions.toCharArray();
        
        for (int k = 0; k < n; k++) {
            int i = (int) arr[k];
            if (dirs[i] == 'R') {
                stack[++top] = i;
            } else {
                while (top >= 0) {
                    int j = stack[top];
                    if (healths[i] > healths[j]) {
                        healths[i]--;
                        healths[j] = 0;
                        top--;
                    } else if (healths[i] < healths[j]) {
                        healths[j]--;
                        healths[i] = 0;
                        break;
                    } else {
                        healths[i] = 0;
                        healths[j] = 0;
                        top--;
                        break;
                    }
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                ans.add(healths[i]);
            }
        }
        return ans;
    }
}