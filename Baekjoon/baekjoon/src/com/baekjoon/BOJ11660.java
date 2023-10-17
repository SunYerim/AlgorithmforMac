package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660 {
    static int N, M, sum;
    static int[][] maze; // 경로 정보 담을 배열
    static int[][] dp; // 누적 합을 저장하는 배열
    static int[] answer; // 정답을 담는 배열
    public static void main(String[] args) throws IOException {
        // 일단 무식한 방법으로
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 미로 입력 완료
        maze = new int[N][N];
        dp = new int[N][N];
        answer = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 계산
        calculatePrefixSum();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            int result = calculateSum(x1, y1, x2, y2);

            answer[i] = result;
        }

        for (int i = 0; i < M; i++) {
            System.out.println(answer[i]);
        }

    }



    // 누적합 -> 공부 더 하기
    private static void calculatePrefixSum() {
        dp[0][0] = maze[0][0];

        for (int i = 1; i < N; i++) {
            dp[0][i] = dp[0][i-1] + maze[0][i];
            dp[i][0] = dp[i-1][0] + maze[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = maze[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }
    }

    private static int calculateSum(int x1, int y1, int x2, int y2) {
        if (x1==0 && y1==0){
            return dp[x2][y2];
        } else if (x1 == 0) {
            return (dp[x2][y2] - dp[x2][y1 - 1]);
        } else if (y1 == 0) {
            return (dp[x2][y2] - dp[x1 - 1][y2]);
        } else {
            return (dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1]);
        }
    }
}
