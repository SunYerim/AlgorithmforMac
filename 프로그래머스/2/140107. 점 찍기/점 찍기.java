import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        // 피타고라스
        // 원점과의 거리
        for (int a = 0; a <= d; a+=k) {
            long b = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(a, 2));
            // // debugging
            // System.out.println(a + ", " + b + " " + (b / k+1));
            answer += (long) b / k+1;
        }
        return answer;
    }
}