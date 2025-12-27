import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        PriorityQueue<Integer> unusedRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            unusedRooms.offer(i);
        }
        
        PriorityQueue<long[]> engagedRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });
        
        int[] count = new int[n];
        
        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;
            
            while (!engagedRooms.isEmpty() && engagedRooms.peek()[0] <= start) {
                unusedRooms.offer((int)engagedRooms.poll()[1]);
            }
            
            if (!unusedRooms.isEmpty()) {
                int room = unusedRooms.poll();
                count[room]++;
                engagedRooms.offer(new long[]{start + duration, room});
            } else {
                long[] current = engagedRooms.poll();
                long finishTime = current[0];
                int room = (int)current[1];
                count[room]++;
                engagedRooms.offer(new long[]{finishTime + duration, room});
            }
        }
        
        int maxMeetings = -1;
        int resultRoom = -1;
        for (int i = 0; i < n; i++) {
            if (count[i] > maxMeetings) {
                maxMeetings = count[i];
                resultRoom = i;
            }
        }
        
        return resultRoom;
    }
}