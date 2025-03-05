import java.util.*;

class Solution {
    // 무조건 균등
    static int[] answer;
    public int[] solution(int n, int s) {
        if (s / n <= 0) {
            answer = new int[]{-1};
        } else {
            answer = new int[n];
            // 우선 몫으로 초기화
            Arrays.fill(answer, s / n);
            
            // 마지막 요소부터
            int remain = s % n;
            for (int i = n - 1; i >= n - remain; i--) {
                answer[i]++;
            }
            
        }
        return answer;
    }
}