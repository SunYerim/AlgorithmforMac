import java.util.*;

// dp
class Solution {
    static long[] dp1, dp2;
    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;
        
        dp1 = new long[n];
        dp2 = new long[n];
        
        dp1[0] = sequence[0];
        dp2[0] = -sequence[0];
        
        long max1 = dp1[0];
        long max2 = dp2[0];
        
        for (int i = 1; i < n; i++) {
            long next1 = (i % 2 == 0) ? sequence[i] : -sequence[i];
            long next2 = (i % 2 == 0) ? -sequence[i] : sequence[i];
            
            dp1[i] = Math.max(next1, next1 + dp1[i-1]);
            dp2[i] = Math.max(next2, next2 + dp2[i-1]);
            
            max1 = Math.max(max1, dp1[i]);
            max2 = Math.max(max2, dp2[i]);
        }
        
        // debugging
        // for (int i = 0; i < n; i++) {
        //     System.out.println(dp1[i] + " " + dp2[i]);
        // }
        
        answer = Math.max(max1, max2);
        return answer;
    }
}