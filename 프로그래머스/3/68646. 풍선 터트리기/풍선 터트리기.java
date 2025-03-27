import java.util.*;

class Solution {
    public int solution(int[] a) {
        // 현재 숫자보다 양 옆의 숫자가 모두 작으면 안 됨
        int answer = 0;
        int size = a.length;
        
        if (size <= 2) return size;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {return o1[0] - o2[0];});
        
        for (int i = 0; i < size; i++) {
            pq.add(new int[]{a[i], i});
        }
        
        // debugging
        // for (int i = 0; i < size; i++) {
        //     int[] curr = pq.poll();
        //     System.out.println(curr[0] + " " + curr[1]);
        // }
        
        int rightMin = Integer.MAX_VALUE;
        
        for (int i = size - 1; i >= 0; i--) {
            // right
            while (!pq.isEmpty() && pq.peek()[1] >= i) {
                int[] curr = pq.poll();
                // System.out.println(curr[0] + " " + curr[1]);
                if (curr[1] != i) rightMin = Math.min(rightMin, curr[0]);
                // System.out.println("rightMin: " + rightMin + " " + i);
            }
            
            // left
            if (!pq.isEmpty() && a[i] < pq.peek()[0] || a[i] < rightMin) answer++;
            
            else if (i == 0) answer++;
            
            rightMin = Math.min(rightMin, a[i]);
        }
        return answer;
    }
}