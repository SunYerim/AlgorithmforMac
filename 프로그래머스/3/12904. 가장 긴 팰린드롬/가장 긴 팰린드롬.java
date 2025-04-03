import java.util.*;
class Solution
{
    static int answer = Integer.MIN_VALUE;
    public int solution(String s)
    {
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = s.length() - 1;
            
            int curLength = 0;
            int idx = 1;
            
            while (left < right) {
                char leftC = s.charAt(left);
                char rightC = s.charAt(right);
                
                if (leftC == rightC) {
                    left++;
                    right--;
                    curLength += 2;
                } else {
                    curLength = 0; // init
                    left = i;
                    right = s.length() - 1 - idx;
                    idx++;
                }
            }
            
            // 중간에 남으면
            if (left == right) {
                curLength++;
            }
            
            answer = Math.max(answer, curLength);
        }

        return answer;
    }
}