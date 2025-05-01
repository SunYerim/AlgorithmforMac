import java.util.*;

class Solution {
    static long answer;
    public long solution(int r1, int r2) {
        answer = 0;
        for (int i = 0; i < r2; i++) {
            if (i < r1) {
                answer += (Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2))) - Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2))) + 1);
            } else {
                answer += Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2)));
            }
        }
        return answer * 4;
    }
}