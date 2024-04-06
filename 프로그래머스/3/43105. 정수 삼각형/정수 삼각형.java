import java.util.*;
class Solution {
    static int[] dp;
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        dp = new int[n];
        // 초기화
        dp[0] = triangle[0][0];
        
        // dp 테이블 구성
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                // 끝쪽이면
                if (j==0)  {
                    dp[j] = dp[j] + triangle[i][j];
                } // 중간꺼면
                else if (j == i) {
                    dp[j] = dp[j-1] + triangle[i][j];
                }
                else {
                    dp[j] = Math.max(dp[j-1], dp[j]) + triangle[i][j];
                }
            }
            
        }
        Arrays.sort(dp);
        
        answer = dp[n-1];
        return answer;
    }
}