import java.util.*;

class Solution {
    static int[][] dp;
    public int solution(int n, int[] money) {
        dp = new int[money.length + 1][n+1];
        
        for (int i = 1; i <= money.length; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) dp[i][j] = 1;
                else if (j - money[i-1] >= 0)
                    dp[i][j] = (dp[i-1][j] + dp[i][j - money[i-1]]) % 1000000007;
                else
                    dp[i][j] = dp[i-1][j] % 1000000007;
            }
        }
        
        // debugging
        // for (int i = 1; i <= money.length; i++) {
        //     for (int j = 0; j <= n; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        return dp[money.length][n];
    }
}