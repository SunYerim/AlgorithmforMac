package com.baekjoon;
 // 다시
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501 {
    public static int N, day, maxSum;
    public static int[][] profit;
    public static int[] dp; // dp테이블에 최대 이익 기록
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        profit = new int[N][2];
        dp = new int[N+2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                profit[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = N; i > 0; i--) {
            if (i + profit[N-i][0] <= N + 1) {
                dp[i] = Math.max(profit[N-i][1] + dp[i + profit[N-i][0]], dp[i+1]);
            } else {
                dp[i] = dp[i+1];
            }
        }


        System.out.println(dp[1]);

    }
}
