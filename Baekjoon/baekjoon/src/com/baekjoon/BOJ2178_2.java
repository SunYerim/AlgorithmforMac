package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178_2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}; // 동 -> 서 -> 남 -> 북
    static int[] dy = {0, 0, -1, 1}; // 동 -> 서 -> 남 -> 북
    public static void main(String[] args) throws IOException {
        // 미로최단 경로 bfs 탐색
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String string = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = string.charAt(j) - '0';
            }
        }
        bfs(0, 0);
        System.out.println(map[N-1][M-1]);
    }


    public static void bfs(int x, int y) {
        // queue
        Queue<int[]> q = new LinkedList<>();

        // 1은 이동할 수 있고 0은 이동할 수 없는 칸임.
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nx = now[0];
            int ny = now[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nx + dx[i];
                int nextY = ny + dy[i];

                // 범위 내에 있으면서
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                    // 길이면서 방문하지 않은 칸일때,
                    if (map[nextX][nextY]==1 && !visited[nextX][nextY]) {
                        q.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        map[nextX][nextY] = map[nx][ny] + 1;

                    }
                }
            }
        }

    }
}
