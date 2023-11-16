package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {
    static int[][][] box;
    static int M, N, H;
    static int[] dx = {1, -1, 0, 0, 0 ,0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        // 3차원 배열의 토마토
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[N][M][H];

        // 3중 for문
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }


        int answer = bfs();
        if (isTomato()) {
            System.out.println(answer);
        }
        else {
            System.out.println(-1);
        }

    }

    public static int bfs() {
        Queue<Node> q = new LinkedList<>();
        int days = 0;

        // 1일때만 일단 큐에 넣어야한다.
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[j][k][i] == 1) {
                        q.offer(new Node(j, k, i, 0));
                    }
                }
            }
        }

        // 큐가 비지 않을때
        while (!q.isEmpty()) {
            Node current = q.poll();
            days = current.count;

            for (int i = 0; i < 6; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                int nz = current.z + dz[i];

                if (nx >= 0 && ny >= 0 && nz >= 0 && nx < N && ny < M && nz < H) {
                    if (box[nx][ny][nz] == 0) {
                        box[nx][ny][nz] = 1;
                        q.offer(new Node(nx, ny, nz, days+1));
                    }
                }
            }
        }
        return days;
    }

    // 다 익었는지 확인
    public static boolean isTomato() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++){
                for (int k = 0; k < M; k++) {
                    if (box[j][k][i] == 0)
                        return false;
                }
            }
        }
        return true;
    }

    static class Node{
        int x;
        int y;
        int z;
        int count;
        public Node(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }
}
