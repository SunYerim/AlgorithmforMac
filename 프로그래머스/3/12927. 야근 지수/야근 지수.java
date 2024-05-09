import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        // queue사용
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            queue.offer(works[i]);
        }
        
        while (n > 0) {
            int tmp = queue.poll();
            if (tmp == 0) break; // 일 못 함
            tmp -= 1;
            queue.offer(tmp);
            n-=1; // 일 한 시간 했음.
        }
        
        for (int num : queue) {
            answer += num * num;
        }
        return answer;
    }
}