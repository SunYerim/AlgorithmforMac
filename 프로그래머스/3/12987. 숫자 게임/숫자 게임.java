import java.util.*;
class Solution {
    // priorityqueue
    public int solution(int[] A, int[] B) {
        int answer = 0;
        PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> queue2 = new PriorityQueue<>(Collections.reverseOrder());
        
        // 넣음
        for (int i = 0; i < A.length; i++) {
            queue1.add(A[i]);
            queue2.add(B[i]);
        }
        
        // 계산
        for (int i = 0; i < A.length; i++) {
            if (queue1.peek() < queue2.peek()) {
                answer++;
                queue1.poll();
                queue2.poll();
            } else {
                queue1.poll();
            }
        }
        return answer;
    }
}