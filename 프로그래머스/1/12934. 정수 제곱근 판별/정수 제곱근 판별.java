import java.util.*;
class Solution {
    public long solution(long n) {
        long answer = 0;
        long number = (long)Math.sqrt(n);
        if (number*number == n) {
            answer = (number+1)*(number+1);
        } else {
            answer = -1;
        }
        
        return answer;
    }
}