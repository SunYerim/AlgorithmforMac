import java.util.*;
import java.io.*;

class Solution {
    static PriorityQueue<Integer> queue;
    public int solution(int[] scoville, int K) {
        queue = new PriorityQueue<>();
        int answer = 0;
        for (int a : scoville) {
            queue.add(a);
        }
        
        int min = queue.peek();
        while (min < K) {
            if (queue.size() >= 2) {
                queue.add(queue.poll() + (queue.poll() * 2));
                min = queue.peek();
                answer++;
            }
            else {
                return -1;
            }
        }
        return answer;
    }
    
}