package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
    static int N;
    static int[][] team;
    static boolean[] visited;
    static int minDiff = Integer.MAX_VALUE; // 능력치 차이의 최소값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        team = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                team[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(minDiff);
    }

    public static void dfs(int start, int count) {
        if (count == N / 2) {
            // 팀 구성이 완료된 경우
            int team1 = 0;
            int team2 = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i] && visited[j]) {
                        team1 += team[i][j];
                    } else if (!visited[i] && !visited[j]) {
                        team2 += team[i][j];
                    }
                }
            }
            int diff = Math.abs(team1 - team2);
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }
}
