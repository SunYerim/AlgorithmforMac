import java.util.*;
// dfs 백트레킹 -> 시간초과
// dp memoization
class Solution {
    static int[][][] dp;
    static int answer = -1;
    public int solution(int[][] info, int n, int m) {
        dp = new int[info.length + 1][n][m];
        // i번째 물건 훔칠때 a가 n, b가 m
        for (int i = 0; i <= info.length; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        dp[0][0][0] = 0;
        
        int aAdd = 0;
        int bAdd = 0;
        
        for (int i = 0; i < info.length; i++) {
            aAdd = info[i][0];
            bAdd = info[i][1];
            
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (dp[i][j][k] == Integer.MAX_VALUE) continue;
                    
                    int nextA = aAdd + j;
                    int nextB = bAdd + k;
                    
                    if (nextA < n) 
                        dp[i+1][nextA][k] = Math.min(dp[i+1][nextA][k], dp[i][j][k] + aAdd);
                    if (nextB < m)
                        dp[i+1][j][nextB] = Math.min(dp[i+1][j][nextB], dp[i][j][k]);
                }
            }
        }
        
        int minA = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                minA = Math.min(minA, dp[info.length][i][j]);
            }
        }
     
        return minA == Integer.MAX_VALUE ? -1 : minA;
    }
}