import java.util.*;

class Solution {
    static StringBuilder answer = new StringBuilder();
    public String solution(int n) {
        
        while (n > 0) {
            int num = n % 3;
            if (num == 0) {
                answer.append(4);
                n = n / 3 - 1;
            } else {
                answer.append(num);
                n /= 3;
            }
        }
        return answer.reverse().toString();
    }
}