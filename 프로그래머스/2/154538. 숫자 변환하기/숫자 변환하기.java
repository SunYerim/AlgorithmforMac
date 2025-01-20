import java.util.*;

class Solution {
    static int[] numbers = new int[1000001]; // visiteing
    static int answer = Integer.MAX_VALUE;
    static Queue<Integer> queue = new LinkedList<>();
    public int solution(int x, int y, int n) {
        Arrays.fill(numbers, Integer.MAX_VALUE);
        queue.offer(x);
        numbers[x] = 0; // 방문함.
        
        while (!queue.isEmpty()) {
            int target = queue.poll();
            int cnt = numbers[target];
            
            visitCheck(target + n, y, cnt + 1);
            visitCheck(target * 2, y, cnt + 1);
            visitCheck(target * 3, y, cnt + 1);
        }
        
        return numbers[y] == Integer.MAX_VALUE ? -1 : numbers[y];
    }
    
    private static void visitCheck(int value, int limit, int cnt) {
        // 초과
        if (value > limit) return;
        
        if (numbers[value] == Integer.MAX_VALUE) {
            numbers[value] = cnt;
            queue.offer(value);
        }
    }
}