import java.util.*;
class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    private static void dfs(int sum, int depth, int[] numbers, int target) {
        // 기저
        if (depth == numbers.length && sum == target) {
            answer++;
            return;
        }
        if (depth >= numbers.length) {
            return;
        }
        // 유도
        dfs(sum+numbers[depth], depth+1, numbers, target);
        dfs(sum-numbers[depth], depth+1, numbers, target);
        
    }
}