import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] alphabets = new int['z' - 'a' + 1];
        Arrays.fill(alphabets, -1);
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (alphabets[ch - 'a'] == -1) {
                answer[i] = -1;
                alphabets[ch - 'a'] = i;
            } else {
                answer[i] = i - alphabets[ch - 'a'];
                alphabets[ch - 'a'] = i;
            }
        }
        
        return answer;
    }
}