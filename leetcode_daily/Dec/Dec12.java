import java.util.*;

class Solution {
    private static class Event {
        int time;
        int type;
        String param;
        Event(int time, int type, String param) {
            this.time = time;
            this.type = type;
            this.param = param;
        }
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int n = events.size();
        Event[] sortedEvents = new Event[n];
        int idx = 0;
        for (List<String> e : events) {
            int time = Integer.parseInt(e.get(1));
            int type = e.get(0).charAt(0) == 'O' ? 0 : 1;
            sortedEvents[idx++] = new Event(time, type, e.get(2));
        }
        
        Arrays.sort(sortedEvents, (a, b) -> {
            if (a.time != b.time) return a.time - b.time;
            return a.type - b.type;
        });
        
        int[] mentions = new int[numberOfUsers];
        int[] onlineTime = new int[numberOfUsers];
        
        for (Event e : sortedEvents) {
            if (e.type == 0) {
                int id = Integer.parseInt(e.param);
                onlineTime[id] = e.time + 60;
            } else {
                if (e.param.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++) mentions[i]++;
                } else if (e.param.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (e.time >= onlineTime[i]) mentions[i]++;
                    }
                } else {
                    String s = e.param;
                    int len = s.length();
                    int i = 0;
                    while (i < len) {
                        if (s.charAt(i) == 'i') {
                            i += 2;
                            int val = 0;
                            while (i < len) {
                                char c = s.charAt(i);
                                if (c == ' ') break;
                                val = val * 10 + (c - '0');
                                i++;
                            }
                            mentions[val]++;
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
        return mentions;
    }
}