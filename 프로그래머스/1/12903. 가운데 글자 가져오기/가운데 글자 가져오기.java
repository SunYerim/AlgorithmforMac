import java.util.*;
class Solution {
    public String solution(String s) {
        String answer = "";
        // 짝수
        if (s.length() % 2 == 0) {
            int len = s.length() / 2;
            char tmp1 = s.charAt(s.length()/2-1);
            char tmp2 = s.charAt(s.length()/2);
            answer += Character.toString(tmp1);
            answer += Character.toString(tmp2);
        }
        
        // 홀수
        if (s.length() % 2 != 0) {
            char tmp = s.charAt(s.length()/2);
            answer = Character.toString(tmp);
        }
        return answer;
    }
}