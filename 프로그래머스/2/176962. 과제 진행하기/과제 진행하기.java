import java.util.*;

class Solution {
    static String[] answer;
    static PriorityQueue<HomeWork> pq;
    public String[] solution(String[][] plans) {
        answer = new String[plans.length];
        pq = new PriorityQueue<>();
        
        for (String[] p : plans) {
            pq.add(new HomeWork(p[0], convertTime(p[1]), Integer.parseInt(p[2])));     
        }
        
        int idx = 0;
        HomeWork curr = pq.poll();
        int now = curr.start;
        
        // 멈춘 과제 관리 stack
        Stack<HomeWork> stack = new Stack<>();
        
        while (true) {
            // 과제 중지
            if (!pq.isEmpty() && now + curr.playTime > pq.peek().start) {
                stack.push(new HomeWork(curr.name, curr.start, curr.playTime - (pq.peek().start - now)));
                now = pq.peek().start;
                curr = pq.poll();        
            }  else {
                // 과제 끝냄
                answer[idx++] = curr.name;
                now += curr.playTime;
                
                // 새로 시작해야된다면
                if (!pq.isEmpty() && now == pq.peek().start) {
                    curr = pq.poll();
                }
                
                // 멈춘 과제를 해야한다면
                else if (!stack.isEmpty()) {
                    curr = stack.pop();
                }
                else if (!pq.isEmpty()) {
                    curr = pq.poll();
                    now = curr.start;
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
    
    static class HomeWork implements Comparable<HomeWork> {
        String name;
        int start, playTime;
        
        public HomeWork(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
        
        @Override
        public int compareTo(HomeWork o) {
            return this.start - o.start;
        }
    }
    
    public static int convertTime(String time) {
        String[] str = time.split(":");
        int min = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
        
        return min;
    }
}