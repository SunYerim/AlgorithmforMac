import java.util.*;
// backtracking
// 각 자리수 5를 기준으로 뒀을때, 5보다 크면 올리고 5보다 작으면 내리는 것이 이득.
// 그렇다면 5는? -> 둘 다 시도
class Solution {
    public int solution(int storey) {
        int answer = backtracking(storey, 0);
        return answer;
    }
    
    private int backtracking(int floor, int count) {
        // 기저 -> 0층에 도달한 경우
        if (floor == 0) {
            return count;
        }
        
        // 현재 자리수 계산 (1의 자리)
        int digit = floor % 10;
        
        // 5보다 작은 경우 -> 내림
        if (digit < 5) {
            return backtracking(floor / 10, count + digit);
        }
        // 6 이상 -> 올림
        else if (digit > 5) {
            return backtracking((floor / 10) + 1, count + (10 - digit));
        }
        // 5일 때 -> 올리는 경우와 내리는 경우 모두 시도
        else {
            // 올리기
            int up = backtracking((floor / 10) + 1, count + 5);
            // 내리기
            int down = backtracking(floor / 10, count + 5);
            // 최소 값 선택
            return Math.min(up, down);
        }
    }
        
}