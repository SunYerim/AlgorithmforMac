import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        String tmp = Long.toString(n);
        int[] ans = new int[tmp.length()];
        for (int i = 0; i < tmp.length(); i++) {
            ans[i] = tmp.charAt(i) - '0';
        }
        
        Integer[] tmp2 = Arrays.stream(ans).boxed().toArray(Integer[]::new); 
        Arrays.sort(tmp2, Collections.reverseOrder());
        
        StringBuilder tmp1 = new StringBuilder();
        for (int i = 0; i < tmp2.length; i++) {
            tmp1.append(tmp2[i]);
        }
        answer = Long.parseLong(tmp1.toString());
        
        return answer;
    }
}
