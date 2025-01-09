import java.util.*;
class Solution {
    static char[] tmp;
    public String solution(String s) {
        String answer = "";
        tmp = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            tmp[i] = s.charAt(i);
        }
        
        Arrays.sort(tmp);
        
        for (int i = 0; i < s.length(); i++) {
            answer += tmp[s.length() - i - 1];
        }
        return answer;
    }
}