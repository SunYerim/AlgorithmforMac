// 정렬 -> pq사용해서 count++
import java.util.*;

class Solution {
    static PriorityQueue<Node> pq;
    static int answer;
    public int solution(int[][] targets) {
        pq = new PriorityQueue<>();
        for (int i = 0; i < targets.length; i++) {
            pq.add(new Node(targets[i][0], targets[i][1]));
        }
        
        Node curr = pq.poll();
        int currEnd = curr.end;
        
        answer = 1;
        
        while (!pq.isEmpty()) {
            Node pos = pq.poll();
            if (pos.start >= currEnd) {
                currEnd = pos.end;
                
                answer++;
            }
        }
        
        return answer;
    }
    
    static class Node implements Comparable<Node>{
        int start, end;
        
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo (Node o) {
            return this.end - o.end;
        }
    }
}