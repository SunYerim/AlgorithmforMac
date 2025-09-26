import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        String[] pronouncable = {"aya", "ye", "woo", "ma"};
        String[] doublePronouns = {"ayaaya", "yeye", "woowoo", "mama"};
        
        int answer = 0;
        for (String s : babbling) {
            boolean hasDouble = false;
            // 연속된 발음이 있는가?
            for (String doubleP : doublePronouns) {
                if (s.contains(doubleP)) {
                    hasDouble = true;
                    break;
                }
            }
            if (hasDouble) continue;
            
            for (String p : pronouncable) {
                s = s.replace(p, " ");
            }
            s = s.replace(" ", "");
            
            if (s.length() == 0) answer++;
        }
        
        return answer;
    }
}