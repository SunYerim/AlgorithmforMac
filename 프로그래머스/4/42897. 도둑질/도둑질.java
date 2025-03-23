import java.util.*;

class Solution {
    static int[][] dp;
    public int solution(int[] money) {
        int answer = 0;
        dp = new int[2][money.length + 1];
        
        dp[0][0] = 0;
        dp[1][0] = 0;
        
        // 0 -> 마지막 값 제외
        // 1 -> 첫번째 값 제외
        for (int i = 0; i < money.length - 1; i++) {
            dp[0][i+1] = money[i];
            dp[1][i+1] = money[i+1];
        }
        
        for (int i = 2; i < dp[0].length; i++) {
            dp[0][i] = Math.max(dp[0][i-2] + dp[0][i], dp[0][i-1]);
            dp[1][i] = Math.max(dp[1][i-2] + dp[1][i], dp[1][i-1]);
        }
        
        // max
        answer = Math.max(dp[0][dp[0].length - 1], dp[1][dp[0].length - 1]);
        
        // for (int i = 0; i < 2; i++) {
        //     for (int j = 0; j < dp[0].length; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        
        return answer;
    }
}