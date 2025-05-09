import java.util.*;

class Solution {
    static String answer;
    static PriorityQueue<Integer> pq; // 크루들 도착 시간
    static Queue<Integer> bus; // 버스 도착 시간
    static List<List<Integer>> list; // 각 버스 시간에 탑승하는 크루들
    static int[] times;
    public String solution(int n, int t, int m, String[] timetable) {
        pq = new PriorityQueue<>();
        bus = new LinkedList<>();
        list = new ArrayList<>();
        times = new int[n];
        
        // 1. crew들 시간 치환해서 pq
        for (int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            int h = Integer.parseInt(time[0]);
            int mi = Integer.parseInt(time[1]);
            pq.add(h * 60 + mi);
        }
        
        // 2. bus 도착 시간
        int st = 9 * 60;
        for (int i = 1; i <= n; i++) {
            bus.add(st);
            st += t;
        }
        
        // 3. 해당 버스 시간에 탑승 가능한 크루들 체크
        int size = bus.size();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int curr = bus.poll(); // 버스 도착시간
            List<Integer> tmp = new ArrayList<>();
            
            while (tmp.size() < m && !pq.isEmpty() && pq.peek() <= curr) {
                tmp.add(pq.poll());
            }
            list.add(tmp);
            times[idx++] = curr;
        }
        
        // 4. 콘이 탑승할 수 있는 시간 계산
        // 마지막 버스 확인
        List<Integer> lastBus = list.get(list.size() - 1);
        System.out.println(lastBus);
        if (lastBus.size() == m) {
            int first = lastBus.get(lastBus.size() - 1) - 1;
            answer = convertTime(first);
        } else {
            answer = convertTime(times[n-1]);
        }
        return answer;
    }
    
    public static String convertTime(int time) {
        StringBuilder t = new StringBuilder();
        String h = String.valueOf(time / 60);
        String min = String.valueOf(time % 60);
        
        if (Integer.parseInt(h) < 10) {
            h = '0' + h;
        }
        if (Integer.parseInt(min) < 10) {
            min = '0' + min;
        }
        
        t.append(h).append(":").append(min);
        
        return t.toString();
    }
}