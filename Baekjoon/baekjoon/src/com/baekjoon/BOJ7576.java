package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {
    static int[][] box; // 상자
    static int M, N;
    static boolean[][] visited; // 방문했니 안했니
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 상자의 가로 크기
        N = Integer.parseInt(st.nextToken()); // 상자의 세로 크기

        box = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = bfs(N, M);

        if (checkAllTomatoes(N, M)) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }

    public static int bfs(int N, int M) {
        Queue<Node> q = new LinkedList<>();
        int days = 0;

        // 익은 토마토의 위치를 큐에 넣음.
        // 익은 토마토만 현재 들어가있음.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 1) {
                    q.offer(new Node(i, j, 0));
                }
            }
        }

        while (!q.isEmpty()) {
            Node current = q.poll();
            days = current.count;
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 범위 내에 있으면서, 토마토가 익었으면 4방향 모두 익음 처리해야됨.
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (box[nx][ny] == 0) {
                        box[nx][ny] = 1;
                        q.offer(new Node(nx, ny, days + 1));
                    }
                }
            }
        }
        return days;
    }

        static boolean checkAllTomatoes(int N, int M) {
            // 모든 토마토 익었니 안익었니.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (box[i][j] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

    static class Node {
        int x;
        int y;
        int count;
        public Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
